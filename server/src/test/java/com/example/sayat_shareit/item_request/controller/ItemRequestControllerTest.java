package com.example.sayat_shareit.item_request.controller;

import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestController;
import com.example.sayat_shareit.item_request.ItemRequestService;
import com.example.sayat_shareit.item_request.dto.ItemRequestCreateDto;
import com.example.sayat_shareit.item_request.dto.ItemRequestMapper;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.sayat_shareit.utils.RequestConstants.USER_HEADER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    @SneakyThrows
    void findById_shouldThrowNotFoundException() {
        int wrongItemRequestId = 9999;
        Mockito.when(itemRequestService.findById(Mockito.anyInt()))
                .thenThrow(new NotFoundException("Запрос не найден"));


        mockMvc.perform(get(("/requests/" + wrongItemRequestId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(("$.message")).value("Запрос не найден"));
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

        ItemRequestCreateDto itemRequestCreateDto = new ItemRequestCreateDto();
        itemRequestCreateDto.setDescription("Table");

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(1);
        itemRequest.setDescription(itemRequestCreateDto.getDescription());
        itemRequest.setRequestor(user);

        Mockito.when(itemRequestService.create(Mockito.any(ItemRequest.class), Mockito.anyInt()))
                .thenReturn(itemRequest);

        String json = objectMapper.writeValueAsString(itemRequestCreateDto);

        mockMvc.perform(post("/requests")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(USER_HEADER, user.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").value("Table"))
                .andExpect(jsonPath("$.requestor.name").value("Bob"))
                .andExpect(jsonPath("$.requestor.email").value("bob@mail.ru"));
    }

    @Test
    @SneakyThrows
    void create_shouldThrowException() {
        int wrongUserId = 9999;
        ItemRequestCreateDto itemRequestCreate = new ItemRequestCreateDto();
        itemRequestCreate.setDescription("Table");

        Mockito.when(itemRequestService.create(Mockito.any(ItemRequest.class), Mockito.anyInt()))
                .thenThrow(new NotFoundException("Пользователь не найден"));

        String json = objectMapper.writeValueAsString(itemRequestCreate);

        mockMvc.perform(post("/requests")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(USER_HEADER, wrongUserId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Пользователь не найден"));
    }
}
