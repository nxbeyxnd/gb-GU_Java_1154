package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    public User saveOrUpdate(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login,password);
    }
}
