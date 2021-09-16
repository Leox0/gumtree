package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.exceptions.ExceptionReason;
import pl.pcz.gumtree.exceptions.GumtreeCopyException;
import pl.pcz.gumtree.model.dao.AuthorityEntity;
import pl.pcz.gumtree.model.dao.ConfirmationToken;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.model.dto.UserRequest;
import pl.pcz.gumtree.model.dto.UserResponse;
import pl.pcz.gumtree.repository.ConfirmationTokenRepository;
import pl.pcz.gumtree.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    AuthorityEntity userRole = AuthorityEntity.builder().name("ROLE_USER").build();
    AuthorityEntity adminRole = AuthorityEntity.builder().name("ROLE_ADMIN").build();

    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    public UserEntity getUserByMailOrNick(String nick) {
        return userRepository.findByMailOrNick(nick).get();
    }

    public UserResponse createUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        UserEntity userEntity = convertUserRequestToUserEntity(userRequest);
        userRepository.save(userEntity);
        ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);
        confirmationTokenRepository.save(confirmationToken);
        sendRegistrationEmail(confirmationToken);
        return convertUserEntityToUserResponse(getUserByMailOrNick(userRequest.getNick()));
    }

    public void confirmUserAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findAllByConfirmationToken(confirmationToken);
        if (token != null) {
            UserEntity user = userRepository.findByMailOrNick(token.getUserEntity().getMail()).get();
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            throw new GumtreeCopyException(ExceptionReason.CONFIRMATION_LINK_IS_INVALID_OR_BROKEN);
        }
    }

    private void sendRegistrationEmail(ConfirmationToken confirmationToken) {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(confirmationToken.getUserEntity().getMail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("mail0java0sender@gmail.com");
            mailMessage.setText("To confirm your account please click here: " + "http://localhost:8080/users/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);
        }

        private void validateUserRequest (UserRequest userRequest){
            if (userRepository.findByMailOrNick(userRequest.getNick()).isPresent()) {
                throw new GumtreeCopyException(ExceptionReason.USER_WITH_MAIL_ACTUALLY_EXISTS);
            }
        }

        private UserResponse convertUserEntityToUserResponse (UserEntity userEntity){
            return UserResponse.builder()
                    .mail(userEntity.getMail())
                    .nick(userEntity.getNick())
                    .build();
        }

        private UserEntity convertUserRequestToUserEntity (UserRequest userRequest){
            return UserEntity.builder()
                    .mail(userRequest.getMail())
                    .nick(userRequest.getNick())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .authorities(List.of(userRole))
                    .isEnabled(false)
                    .build();
        }

    }
