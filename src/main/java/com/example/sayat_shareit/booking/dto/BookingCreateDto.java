package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingCreateDto {
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BookingStatus status;
}
