package com.example.sayat_shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemCreateDto {
    @NotBlank(message = "Название предмета не может быть пустым")
    private String name;

    @NotBlank(message = "Описание предмета не может быть пустым")
    private String description;

    @NotNull(message = "Признак доступности предмета не может быть пустым")
    private Boolean available;
}
