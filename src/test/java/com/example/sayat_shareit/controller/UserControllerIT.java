package com.example.sayat_shareit.controller;

import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserResponseDto;
import com.example.sayat_shareit.user.dto.UserUpdateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void updateNameAndEmail() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("Bob");
        userCreateDto.setEmail("bob@mail.ru");

        String json = objectMapper.writeValueAsString(userCreateDto);

        String responseJson = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.email").value("bob@mail.ru"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        int id = objectMapper.readValue(responseJson, UserResponseDto.class)
                .getId();

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setName("Mo");
        userUpdateDto.setEmail("mo@mail.ru");

        String jsonUpdate = objectMapper.writeValueAsString(userUpdateDto);

        mockMvc.perform(patch("/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Mo"))
                .andExpect(jsonPath("$.email").value("mo@mail.ru"));
    }
}
