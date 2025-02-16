package com.example.sayat_shareit.user.service;

import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
public class UserServiceIT {

    @Autowired
    UserService userService;

    @Test
    void deleteById() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("Bob@mail.ru");

        User savedUser = userService.create(user);

        Assertions.assertDoesNotThrow(() -> userService.findById(savedUser.getId()));
        userService.deleteById(savedUser.getId());
        Assertions.assertThrows(NotFoundException.class,() -> userService.findById(savedUser.getId()));
    }

    @Test
    void findAll_shouldReturnNonEmptyList() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("Bob@mail.ru");

        User user2 = new User();
        user2.setName("Kira");
        user2.setEmail("Kira@mail.ru");

        userService.create(user);
        userService.create(user2);

        List<User> users = userService.findAll();

        Assertions.assertEquals(2, users.size());
    }

    @Test
    void findAll_shouldReturnEmptyList() {
        List<User> users = userService.findAll();

        Assertions.assertEquals(0, users.size());
    }
}
