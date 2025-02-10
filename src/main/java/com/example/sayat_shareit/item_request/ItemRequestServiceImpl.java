package com.example.sayat_shareit.item_request;

import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    private final ItemRequestRepository itemRequestRepository;
    private final UserRepository userRepository;

    @Override
    public ItemRequest create(ItemRequest itemRequest, int requestorId) {
        User user = userRepository.findById(requestorId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        itemRequest.setRequestor(user);
        return itemRequestRepository.save(itemRequest);
    }

    @Override
    public ItemRequest findById(int itemRequestId) {
        return itemRequestRepository.findById(itemRequestId)
                .orElseThrow(() -> new NotFoundException("Запрос с таким ID не найден"));
    }

    @Override
    public List<ItemRequest> findAllByRequestorId(int requestorId) {
        return itemRequestRepository.findByRequestorId(requestorId);
    }
}
