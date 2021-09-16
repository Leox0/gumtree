package pl.pcz.gumtree.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pcz.gumtree.model.dao.AuthorityEntity;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
@Transactional
public class UserDataProvider implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        AuthorityEntity userRole = AuthorityEntity.builder().name("ROLE_USER").build();
        AuthorityEntity adminRole = AuthorityEntity.builder().name("ROLE_ADMIN").build();

        UserEntity u1 = userRepository.save(UserEntity.builder()
                .mail("user@mail.pl")
                .nick("user")
                .password(passwordEncoder.encode("user123"))
                .authorities(Collections.singletonList(userRole))
                .isEnabled(true)
                .build());

        UserEntity a1 = userRepository.save(UserEntity.builder()
                .mail("admin@mail.pl")
                .nick("admin")
                .password(passwordEncoder.encode("admin123"))
                .authorities(List.of(userRole,adminRole))
                .isEnabled(true)
                .build());


    }

}
