package pl.pcz.gumtree.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public List<UserEntity> getAllUsers() {
        return userService.getUser();
    }
}
