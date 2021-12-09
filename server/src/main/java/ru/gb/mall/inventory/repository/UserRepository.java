package ru.gb.mall.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.mall.inventory.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserByLoginAndPassword(String login, String password);
}