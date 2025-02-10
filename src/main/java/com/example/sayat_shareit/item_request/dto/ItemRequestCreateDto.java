package com.example.sayat_shareit.item_request.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemRequestCreateDto {
    @NotBlank(message = "Описание запроса не должно быть пустым")
    String description;
}
