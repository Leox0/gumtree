package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.model.dao.UserEntity;
import pl.pcz.gumtree.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getUser(){
        return userRepository.findAll();
    }



}
