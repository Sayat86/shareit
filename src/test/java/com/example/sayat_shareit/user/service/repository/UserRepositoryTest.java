package com.example.sayat_shareit.user.service.repository;

import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmailShouldReturnEmptyOptional() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@mail.ru");

        userRepository.save(user);
        Optional<User> optional = userRepository.findByEmail("kira@mail.ru");
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    void findByEmailShouldReturnPresentOptional() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@mail.ru");

        userRepository.save(user);
        Optional<User> optional = userRepository.findByEmail("bob@mail.ru");
        Assertions.assertTrue(optional.isPresent());
    }
}
