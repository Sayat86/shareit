package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
}
