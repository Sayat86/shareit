package com.example.sayat_shareit.controller;

import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserController;
import com.example.sayat_shareit.user.UserService;
import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @SpyBean
    UserMapper userMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void findById_test() {
        User user = new User();
        user.setId(1);
        user.setName("Bob");
        user.setEmail("bob@mail.ru");

        Mockito.when(userService.findById(Mockito.anyInt()))
                .thenReturn(user);

        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("bob@mail.ru"));
    }

    @Test
    @SneakyThrows
    void findAll_shouldReturnUser() {
        User user = new User();
        user.setId(1);
        user.setName("Bob");
        user.setEmail("bob@mail.ru");
        User user2 = new User();
        user2.setId(2);
        user2.setName("Kira");
        user2.setEmail("kira@mail.ru");

        Mockito.when(userService.findAll())
                .thenReturn(List.of(user, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Bob"))
                .andExpect(jsonPath("$[0].email").value("bob@mail.ru"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Kira"))
                .andExpect(jsonPath("$[1].email").value("kira@mail.ru"));
    }

    @Test
    @SneakyThrows
    void createTest() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("Bob");
        userCreateDto.setEmail("bob@mail.ru");

        User user = new User();
        user.setId(1);
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());

        Mockito.when(userService.create(Mockito.any(User.class)))
                .thenReturn(user);

        String json = objectMapper.writeValueAsString(userCreateDto);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.email").value("bob@mail.ru"));
    }
}
