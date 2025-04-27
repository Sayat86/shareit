package com.example.sayat_shareit.item;

import com.example.sayat_shareit.item.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @PostMapping
    public ItemResponseDto create(@RequestBody ItemCreateDto itemCreate,
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
    public List<ItemResponseDto> findAllByOwnerId(@RequestHeader(USER_HEADER) int userId,
                                                  @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                  @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = from / size;
        return itemMapper.toResponse(itemService.findAllByOwnerId(userId, page, size));
    }

    @GetMapping("/search")
    public List<ItemResponseDto> search(@RequestParam String text,
                                        @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                        @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = from / size;
        return itemMapper.toResponse(itemService.search(text, page, size));
    }

    @PostMapping("/{itemId}/comment")
    public CommentResponseDto createComment(@RequestBody CommentCreateDto commentCreate,
                                            @PathVariable int itemId,
                                            @RequestHeader(USER_HEADER) int userId) {
        Comment comment = commentMapper.fromCreate(commentCreate);
        return commentMapper.toResponse(commentService.create(comment, itemId, userId));
    }
}