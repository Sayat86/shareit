package com.example.sayat_shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotBlank(message = "Имя пользователя не может быть пустой")
    private String name;

    @NotBlank(message = "Почта пользователя не может быть пустой")
    @Email(message = "Почта должна быть в формате \"user@mail.com\"")
    private String email;
}
