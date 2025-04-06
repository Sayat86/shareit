package com.example.sayat_shareit.item_request;

import com.example.sayat_shareit.item_request.dto.ItemRequestCreateDto;
import com.example.sayat_shareit.item_request.dto.ItemRequestMapper;
import com.example.sayat_shareit.item_request.dto.ItemRequestResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class ItemRequestController {
    private final ItemRequestMapper itemRequestMapper;
    private final ItemRequestService itemRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRequestResponseDto create(@Valid @RequestBody ItemRequestCreateDto itemRequestCreate,
                                         @RequestHeader(USER_HEADER) int requestorId) {
        ItemRequest itemRequest = itemRequestMapper.fromCreate(itemRequestCreate);
        return itemRequestMapper.toResponse(itemRequestService.create(itemRequest, requestorId));
    }

    @GetMapping("/{requestId}")
    public ItemRequestResponseDto findById(@PathVariable int requestId) {
        return itemRequestMapper.toResponse(itemRequestService.findById(requestId));
    }

    @GetMapping
    public List<ItemRequestResponseDto> findAllByRequestorId(@RequestHeader(USER_HEADER) int requestorId,
                                                             @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                             @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = size / from;
        return itemRequestMapper.toResponseList(itemRequestService.findAllByRequestorId(requestorId, page, size));
    }

    @GetMapping("/all")
    public List<ItemRequestResponseDto> findAllByNotRequestorId(@RequestHeader(USER_HEADER) int requestorId,
                                                                @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                                @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = size / from;
        return itemRequestMapper.toResponseList(itemRequestService.findAllByRequestorIdNot(requestorId, page, size));
    }
}
