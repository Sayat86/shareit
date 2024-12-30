package com.example.sayat_shareit.item.dto;

import com.example.sayat_shareit.item.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {
    public void merge(Item existingItem, Item updateItem) {
        if (updateItem.getName() != null && !updateItem.getName().isBlank()) {
            existingItem.setName(updateItem.getName());
        }

        if (updateItem.getDescription() != null && !updateItem.getDescription().isBlank()) {
            existingItem.setDescription(updateItem.getDescription());
        }

        if (updateItem.getAvailable() != null) {
            existingItem.setAvailable(updateItem.getAvailable());
        }
    }

    public Item fromCreate(ItemCreateDto itemCreate) {
        Item item = new Item();
        item.setName(itemCreate.getName());
        item.setDescription(itemCreate.getDescription());
        item.setAvailable(itemCreate.getAvailable());
        return item;
    }

    public Item fromUpdate(ItemUpdateDto itemUpdate) {
        Item item = new Item();
        item.setName(itemUpdate.getName());
        item.setDescription(itemUpdate.getDescription());
        item.setAvailable(itemUpdate.getAvailable());
        return item;
    }

    public ItemResponseDto toResponse(Item item) {
        ItemResponseDto itemResponse = new ItemResponseDto();
        itemResponse.setId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setAvailable(item.getAvailable());
        return itemResponse;
    }

    public List<ItemResponseDto> toResponse(List<Item> items) {
        return items.stream()
                .map(this::toResponse)
                .toList();
    }
}
