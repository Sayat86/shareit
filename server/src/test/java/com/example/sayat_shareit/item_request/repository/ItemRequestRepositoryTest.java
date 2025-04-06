package com.example.sayat_shareit.item_request.repository;

import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestRepository;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ItemRequestRepositoryTest {
    @Autowired
    ItemRequestRepository itemRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByRequestorIdShouldReturnNonEmptyList() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@mail.ru");
        userRepository.save(user);

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription("Table");
        itemRequest.setRequestor(user);
        itemRequestRepository.save(itemRequest);
        List<ItemRequest> itemRequests = itemRequestRepository.findByRequestorId(user.getId());
        Assertions.assertEquals(1, itemRequests.size());
    }

    @Test
    void findByRequestorIdShouldReturnEmptyList() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@mail.ru");
        userRepository.save(user);

        List<ItemRequest> itemRequests = itemRequestRepository.findByRequestorId(user.getId());
        Assertions.assertTrue(itemRequests.isEmpty());
    }
}
