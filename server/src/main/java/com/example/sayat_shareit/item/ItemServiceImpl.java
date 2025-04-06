package com.example.sayat_shareit.item;

import com.example.sayat_shareit.exception.ForbiddenException;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item.dto.ItemMapper;
import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.item_request.ItemRequestRepository;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;
    private final ItemRequestRepository itemRequestRepository;

    @Override
    public Item create(Item item, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        if (item.getRequest() != null) {
            ItemRequest itemRequest = itemRequestRepository.findById(item.getRequest().getId())
                    .orElseThrow(() -> new NotFoundException("Запрос по ID не найден"));
            item.setRequest(itemRequest);
        }
        item.setOwner(user);
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item updateItem, int itemId, int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        Item itemExisting = findById(itemId);
        if (itemExisting.getOwner().getId() != userId) {
            throw new ForbiddenException("Данный пользователь не является владельцем предмета");
        }
        itemMapper.merge(itemExisting, updateItem);
        return itemRepository.save(itemExisting);
    }

    @Override
    public Item findById(int itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Предмет с таким ID не найден"));
    }

    @Override
    public void deleteById(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> findAllByOwnerId(int ownerId, int page, int size) {
        return itemRepository.findByOwnerId(ownerId, PageRequest.of(page, size))
                .getContent();
    }

    @Override
    public List<Item> search(String text, int page, int size) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        return itemRepository.search(text, PageRequest.of(page, size))
                .getContent();
    }
}
