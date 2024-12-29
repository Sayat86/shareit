package com.example.sayat_shareit.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item create(Item item);

    Item update(Item item, int id);

    Optional<Item> findById(int id);

    void deleteById(int id);

    List<Item> findAll();

    List<Item> findByOwnerId(int ownerId);
}
