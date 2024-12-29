package com.example.sayat_shareit.item.dto;

import lombok.Data;

@Data
public class ItemResponseDto {
    private int id;
    private String name;
    private String description;
    private Boolean available;
}
