package com.example.sayat_shareit.item_request;

import com.example.sayat_shareit.item_request.dto.ItemRequestCreateDto;
import com.example.sayat_shareit.item_request.dto.ItemRequestMapper;
import com.example.sayat_shareit.item_request.dto.ItemRequestResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.USER_HEADER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class ItemRequestController {
    private final ItemRequestMapper itemRequestMapper;
    private final ItemRequestService itemRequestService;


    @PostMapping
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
    public List<ItemRequestResponseDto> findAllByRequestorId(@RequestHeader(USER_HEADER) int requestorId) {
        return itemRequestMapper.toResponseList(itemRequestService.findAllByRequestorId(requestorId));
    }
}
