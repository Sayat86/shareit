package com.example.sayat_shareit.item_request.service;

import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestRepository;
import com.example.sayat_shareit.item_request.ItemRequestService;
import com.example.sayat_shareit.item_request.dto.ItemRequestMapper;
import com.example.sayat_shareit.user.User;
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
public class ItemRequestServiceTest {

    @Mock
    ItemRequestRepository itemRequestRepository;

    @InjectMocks
    ItemRequestService itemRequestService;

    @Spy
    ItemRequestMapper itemRequestMapper;

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
}
