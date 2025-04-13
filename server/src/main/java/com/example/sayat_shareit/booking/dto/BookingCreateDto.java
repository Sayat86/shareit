package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingCreateDto {
    private int itemId;

    @NotNull
    @FutureOrPresent(message = "Дата бронирования не должна быть в прошлом")
    private LocalDateTime start;

    @NotNull
    @Future(message = "Дата бонирования должна быть в будущем")
    private LocalDateTime end;
}
