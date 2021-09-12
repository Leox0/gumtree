package pl.pcz.gumtree.configurate;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
@Transactional
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByMailOrNick(username).get();
        List<String> userAuthorities = userEntity.getAuthorities().stream().map(authorityEntity -> authorityEntity.getName()).collect(Collectors.toList());

        return User.builder()
                .username(userEntity.getMail())
                .password(userEntity.getPassword())
                .authorities(userAuthorities.toArray(String[]::new))
                .build();
    }
}

