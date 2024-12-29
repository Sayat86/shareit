package com.example.sayat_shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Integer, Item> items = new HashMap<>();
    private int uniqueId = 1;

    @Override
    public Item create(Item item) {
        item.setId(uniqueId++);
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item update(Item item, int id) {
        items.put(id, item);
        return item;
    }

    @Override
    public Optional<Item> findById(int id) {
        Item item = items.get(id);
        return Optional.ofNullable(item);
    }

    @Override
    public void deleteById(int id) {
        items.remove(id);
    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(items.values());
    }

    @Override
    public List<Item> findByOwnerId(int ownerId) {
        return items.values().stream()
                .filter(item -> item.getOwner().getId() == ownerId)
                .toList();
    }
}
