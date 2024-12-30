package com.example.sayat_shareit.item;

import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemMapper;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.USER_HEADER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ItemResponseDto create(@Valid @RequestBody ItemCreateDto itemCreate,
                                  @RequestHeader(USER_HEADER) int userId) {
        Item item = itemMapper.fromCreate(itemCreate);
        return itemMapper.toResponse(itemService.create(item, userId));
    }

    @PatchMapping("/{itemId}")
    public ItemResponseDto update(@RequestBody ItemUpdateDto itemUpdate,
                                  @RequestHeader(USER_HEADER) int userId,
                                  @PathVariable int itemId) {
        Item item = itemMapper.fromUpdate(itemUpdate);
        return itemMapper.toResponse(itemService.update(item, itemId, userId));
    }

    @GetMapping("/{itemId}")
    public ItemResponseDto findById(@PathVariable int itemId) {
        return itemMapper.toResponse(itemService.findById(itemId));
    }

    @GetMapping
    public List<ItemResponseDto> findAllByOwnerId(@RequestHeader(USER_HEADER) int userId) {
        return itemMapper.toResponse(itemService.findAllByOwnerId(userId));
    }

    @GetMapping("/search")
    public List<ItemResponseDto> search(@RequestParam String text) {
        return itemMapper.toResponse(itemService.search(text));
    }
}