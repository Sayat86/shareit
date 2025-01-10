package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.BookingStatus;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.user.dto.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private UserResponseDto booker;
    private ItemResponseDto item;
}
