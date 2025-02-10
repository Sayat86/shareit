package com.example.sayat_shareit.item_request.dto;

import com.example.sayat_shareit.item_request.ItemRequest;
import com.example.sayat_shareit.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemRequestMapper {

    private final UserMapper userMapper;
    public ItemRequest fromCreate(ItemRequestCreateDto itemRequestCreate) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription(itemRequestCreate.getDescription());
        return itemRequest;
    }

    public ItemRequestResponseDto toResponse(ItemRequest itemRequest) {
        ItemRequestResponseDto itemRequestResponseDto = new ItemRequestResponseDto();
        itemRequestResponseDto.setId(itemRequest.getId());
        itemRequestResponseDto.setDescription(itemRequest.getDescription());
        itemRequestResponseDto.setRequestor(userMapper.toResponse(itemRequest.getRequestor()));
        return itemRequestResponseDto;
    }

    public List<ItemRequestResponseDto> toResponseList(List<ItemRequest> itemRequests) {
        return itemRequests.stream()
                .map(this::toResponse)
                .toList();
    }
}
