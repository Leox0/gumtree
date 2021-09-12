package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.exceptions.ExceptionReason;
import pl.pcz.gumtree.exceptions.GumtreeCopyException;
import pl.pcz.gumtree.exceptions.GumtreeErrorResponse;
import pl.pcz.gumtree.model.dao.AuthorityEntity;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.model.dto.UserRequest;
import pl.pcz.gumtree.model.dto.UserResponse;
import pl.pcz.gumtree.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthorityEntity userRole = AuthorityEntity.builder().name("ROLE_USER").build();
    AuthorityEntity adminRole = AuthorityEntity.builder().name("ROLE_ADMIN").build();

    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    public UserEntity getUserByNick(String nick) {
        return userRepository.findByMailOrNick(nick).get();
    }

    public UserResponse createUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        UserEntity userEntity = convertUserRequestToUserEntity(userRequest);
        userRepository.save(userEntity);
        return convertUserEntityToUserResponse(getUserByNick(userRequest.getNick()));
    }

    private void validateUserRequest(UserRequest userRequest) {
        if (userRepository.findByMailOrNick(userRequest.getNick()).isPresent()) {
            throw new GumtreeCopyException(ExceptionReason.USER_WITH_MAIL_ACTUALLY_EXISTS);
        }
    }

    private UserResponse convertUserEntityToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .mail(userEntity.getMail())
                .nick(userEntity.getNick())
                .build();
    }

    private UserEntity convertUserRequestToUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .mail(userRequest.getMail())
                .nick(userRequest.getNick())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .authorities(List.of(userRole))
                .build();
    }

}
