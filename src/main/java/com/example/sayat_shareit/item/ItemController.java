package com.example.sayat_shareit.item;

import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemMapper;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemResponseDto create(@Valid @RequestBody ItemCreateDto itemCreate,
                                  @RequestHeader("X-Sharer-User-Id") int userId) {
        Item item = itemMapper.fromCreate(itemCreate);
        return itemMapper.toResponse(itemService.create(item, userId));
    }

    @PatchMapping("/{itemId}")
    public ItemResponseDto update(@RequestBody ItemUpdateDto itemUpdate,
                                  @RequestHeader("X-Sharer-User-Id") int userId) {
        Item item = itemMapper.fromUpdate(itemUpdate);
        return itemMapper.toResponse(itemService.update(item, userId));
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findById(@PathVariable int id) {
        return itemMapper.toResponse(itemService.findById(id));
    }
    @GetMapping
    public List<ItemResponseDto> findAllByOwnerId(@RequestHeader("X-Sharer-User-Id") int userId) {
        return itemMapper.toResponse(itemService.findAllByOwnerId(userId));
    }
}