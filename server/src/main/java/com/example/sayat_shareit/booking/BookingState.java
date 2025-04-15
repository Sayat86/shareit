package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.exception.BadRequestException;

public enum BookingState {
    ALL, CURRENT, PAST, FUTURE, WAITING, REJECTED;

    public static BookingState from(String value) {
        try {
            return BookingState.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestException("Unknown state: " + value);
        }
    }
}
