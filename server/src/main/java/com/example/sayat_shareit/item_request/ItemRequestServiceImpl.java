package com.example.sayat_shareit.item_request;

import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    private final ItemRequestRepository itemRequestRepository;
    private final UserRepository userRepository;

    @Override // 2
    public ItemRequest create(ItemRequest itemRequest, int requestorId) {
        User user = userRepository.findById(requestorId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        itemRequest.setRequestor(user);
        return itemRequestRepository.save(itemRequest);
    }

    @Override // 2
    public ItemRequest findById(int itemRequestId) {
        return itemRequestRepository.findById(itemRequestId)
                .orElseThrow(() -> new NotFoundException("Запрос с таким ID не найден"));
    }

    @Override // 1
    public List<ItemRequest> findAllByRequestorId(int requestorId, int page, int size) {
        return itemRequestRepository.findByRequestorId(requestorId, PageRequest.of(page, size)).getContent();
    }

    @Override // 1
    public List<ItemRequest> findAllByRequestorIdNot(int requestorId, int page, int size) {
        return itemRequestRepository.findAllByRequestorIdNot(requestorId, PageRequest.of(page, size)).getContent();
    }
}
