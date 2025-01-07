package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapper {
    public Booking fromCreate(BookingCreateDto bookingCreate) {
        Booking booking = new Booking();
        booking.setStartDate(bookingCreate.getStartDate());
        booking.setEndDate(bookingCreate.getEndDate());
        booking.setStatus(bookingCreate.getStatus());
        return booking;
    }

    public Booking fromUpdate(BookingUpdateDto bookingUpdate) {
        Booking booking = new Booking();
        booking.setStartDate(bookingUpdate.getStartDate());
        booking.setEndDate(bookingUpdate.getEndDate());
        booking.setStatus(bookingUpdate.getStatus());
        return booking;
    }

    public BookingResponseDto toResponseBooking(Booking booking) {
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.setId(booking.getId());
        bookingResponse.setStartDate(booking.getStartDate());
        bookingResponse.setEndDate(booking.getEndDate());
        bookingResponse.setStatus(booking.getStatus());
        return bookingResponse;
    }

    public List<BookingResponseDto> toResponseBooking(List<Booking> bookings) {
        return bookings.stream()
                .map(this::toResponseBooking)
                .toList();
    }

    public void mergeBooking(Booking existingBooking, Booking updateBooking) {
        if (updateBooking.getStartDate() != null) {
            existingBooking.setStartDate(updateBooking.getStartDate());
        }

        if (updateBooking.getEndDate() != null) {
            existingBooking.setEndDate(updateBooking.getEndDate());
        }

        if (updateBooking.getStatus() != null) {
            existingBooking.setStatus(updateBooking.getStatus());
        }
    }
}
