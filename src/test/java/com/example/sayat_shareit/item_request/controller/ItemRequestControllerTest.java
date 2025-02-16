package com.example.sayat_shareit.item_request.controller;

import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestController;
import com.example.sayat_shareit.item_request.ItemRequestService;
import com.example.sayat_shareit.item_request.dto.ItemRequestMapper;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.dto.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ItemRequestController.class, ItemRequestMapper.class, UserMapper.class})
public class ItemRequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemRequestService itemRequestService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void findById_test() {
        User user = new User();
        user.setId(1);
        user.setName("Bob");
        user.setEmail("bob@mail.ru");

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(1);
        itemRequest.setDescription("Table");
        itemRequest.setRequestor(user);

        Mockito.when(itemRequestService.findById(Mockito.anyInt()))
                .thenReturn(itemRequest);

        mockMvc.perform(get("/requests/" + itemRequest.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Table"))
                .andExpect(jsonPath("$.requestor.id").value(user.getId()))
                .andExpect(jsonPath("$.requestor.name").value(user.getName()))
                .andExpect(jsonPath("$.requestor.email").value(user.getEmail()));
    }
}
