package com.example.sayat_shareit.service;

import com.example.sayat_shareit.exception.ConflictException;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import com.example.sayat_shareit.user.UserServiceImpl;
import com.example.sayat_shareit.user.dto.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Spy
    UserMapper userMapper;

    @Test
    void findById_shouldReturnUser() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Bob");

        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(user));

        User actual = userService.findById(userId);

        assertEquals(userId, actual.getId());
        assertEquals(user.getName(), actual.getName());
    }

    @Test
    void findById_shouldThrowException() {
        int userId = 9999;
        String expectedMessage = "Пользователь с таким ID не найден";

        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> userService.findById(userId));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void shouldUpdateUser() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Bob");
        user.setEmail("sayat@mail.ru");

        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);

        User updateUser = new User();
        updateUser.setName("Kira");
        updateUser.setEmail("s@mail.ru");

        User actual = userService.update(updateUser, userId);

        assertEquals(userId, actual.getId());
        assertEquals(updateUser.getName(), actual.getName());
        assertEquals(updateUser.getEmail(), actual.getEmail());
    }

    @Test
    void update_shouldThrowException() {
        int userId = 9999;
        String expectedMessage = "Пользователь с таким ID не найден";
        User user = new User();
        user.setId(userId);
        user.setName("Mo");
        user.setEmail("s@mail.ru");

        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> userService.update(user, userId));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void update_shouldThrowExceptionWhenEmailExist() {
        String expectedMessage = "Пользователь с такой почтой уже существует";
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setName("Mo");
        existingUser.setEmail("s@mail.ru");

        User updatedUser = new User();
        updatedUser.setEmail("s@mail.ru");

        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(existingUser));

        ConflictException ex = assertThrows(ConflictException.class, () -> userService.update(updatedUser, 2));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void update_shouldThrowExceptionWhenEmailExistAndSameId() {
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setName("Mo");
        existingUser.setEmail("s@mail.ru");

        User updatedUser = new User();
        updatedUser.setEmail("s@mail.ru");

        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(existingUser);

        User actual = userService.update(updatedUser, 1);

        assertEquals(existingUser.getEmail(), actual.getEmail());
    }

    @Test
    void create_shouldThrowExceptionWhenEmailExist() {
        String expectedMessage = "Пользователь с такой почтой уже существует";
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setName("Mo");
        existingUser.setEmail("s@mail.ru");

        User creatingUser = new User();
        creatingUser.setEmail("s@mail.ru");

        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(existingUser));

        ConflictException ex = assertThrows(ConflictException.class, () -> userService.create(creatingUser));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void create_shouldSaveUser() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("sayat@mail.ru");

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);

        User actual = userService.create(user);

        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getEmail(), actual.getEmail());
    }
}
