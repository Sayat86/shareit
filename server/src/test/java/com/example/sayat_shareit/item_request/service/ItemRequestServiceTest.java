package com.example.sayat_shareit.item_request.service;

import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestRepository;
import com.example.sayat_shareit.item_request.ItemRequestServiceImpl;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ItemRequestServiceTest {

    @Mock
    ItemRequestRepository itemRequestRepository;

    @InjectMocks
    ItemRequestServiceImpl itemRequestService;

    @Mock
    UserRepository userRepository;

    @Test
    void findById_shouldReturnItemRequest() {
        int itemRequestId = 1;
        User user = new User();
        user.setId(1);
        user.setName("Bob");
        user.setEmail("bob@mail.ru");
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(itemRequestId);
        itemRequest.setDescription("Table");
        itemRequest.setRequestor(user);

        Mockito.when(itemRequestRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(itemRequest));

        ItemRequest actual = itemRequestService.findById(itemRequestId);

        assertEquals(itemRequestId, actual.getId());
        assertEquals(itemRequest.getDescription(), actual.getDescription());

        assertEquals(user.getId(), actual.getRequestor().getId());
        assertEquals(user.getName(), actual.getRequestor().getName());
        assertEquals(user.getEmail(), actual.getRequestor().getEmail());
    }

    @Test
    void findById_shouldThrowException() {
        int itemRequestId = 9999;
        String expectedMessage = "Запрос с таким ID не найден";

        Mockito.when(itemRequestRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> itemRequestService.findById(itemRequestId));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void create_shouldSaveItemRequest() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Bob");
        user.setEmail("bob@mail.ru");

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription("Table");

        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(user));

        Mockito.when(itemRequestRepository.save(Mockito.any(ItemRequest.class)))
                .thenReturn(itemRequest);

        ItemRequest actual = itemRequestService.create(itemRequest, userId);

        assertEquals(itemRequest.getDescription(), actual.getDescription());
        assertEquals(itemRequest.getRequestor().getName(), actual.getRequestor().getName());
        assertEquals(itemRequest.getRequestor().getEmail(), actual.getRequestor().getEmail());
    }

    @Test
    void create_shouldThrowExceptionWhenUserExist() {
        String expectedMessage = "Пользователь с таким ID не найден";
        int wrongUserId = 1;

        ItemRequest creatingItemRequest = new ItemRequest();
        creatingItemRequest.setDescription("Table");


        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> itemRequestService.create(creatingItemRequest, wrongUserId));
        assertEquals(expectedMessage, ex.getMessage());
    }
}
