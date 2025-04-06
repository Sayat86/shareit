package com.example.sayat_shareit.gateway.item;

import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.sayat_shareit.utils.RequestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemClient itemClient;

    @GetMapping
    public ResponseEntity<Object> findAllByOwnerId(@RequestHeader(USER_HEADER) int userId) {
        return itemClient.findAllByOwnerId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return itemClient.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ItemCreateDto itemCreateDto,
                                         @RequestHeader(USER_HEADER) int userId) {
        return itemClient.create(itemCreateDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@Valid @RequestBody ItemUpdateDto itemUpdateDto,
                                         @RequestHeader(USER_HEADER) int userId,
                                         @PathVariable int itemId) {
        return itemClient.update(itemUpdateDto, userId, itemId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String text,
                                         @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                         @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        return itemClient.search(text, from, size);
    }
}
