package pl.pcz.gumtree.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pcz.gumtree.repository.user.UserEntity;
import pl.pcz.gumtree.repository.user.UserRepository;

@Component
@RequiredArgsConstructor
public class UserResolver implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.save(UserEntity.builder()
                .mail("user@mail.pl")
                .nick("user")
                .password("user123")
                .build());

        userRepository.save(UserEntity.builder()
                .mail("admin@mail.pl")
                .nick("admin")
                .password("admin123")
                .build());
    }
}
