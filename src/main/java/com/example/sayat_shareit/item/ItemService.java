package com.example.sayat_shareit.item;

import java.util.List;

public interface ItemService {
    Item create(Item item, int userId);

    Item update(Item item, int itemId, int userId);

    Item findById(int itemId);

    void deleteById(int itemId);

    List<Item> findAllByOwnerId(int ownerId);
}
