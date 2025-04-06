package com.example.sayat_shareit.item_request.dto;

import com.example.sayat_shareit.user.dto.UserResponseDto;
import lombok.Data;

@Data
public class ItemRequestResponseDto {
    int id;
    String description;
    UserResponseDto requestor;
}
