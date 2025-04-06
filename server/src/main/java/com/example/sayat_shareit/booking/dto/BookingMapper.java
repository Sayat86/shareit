package com.example.sayat_shareit.booking.dto;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemMapper;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import com.example.sayat_shareit.user.dto.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final ItemMapper itemMapper;
    private final UserMapper userMapper;

    public Booking fromCreate(BookingCreateDto bookingCreate) {
        Booking booking = new Booking();
        Item item = new Item();
        item.setId(bookingCreate.getItemId());
        booking.setItem(item);
        booking.setStartDate(bookingCreate.getStart());
        booking.setEndDate(bookingCreate.getEnd());
        return booking;
    }

    public BookingResponseDto toResponseBooking(Booking booking) {
        BookingResponseDto bookingResponse = new BookingResponseDto();
        bookingResponse.setId(booking.getId());
        bookingResponse.setStart(booking.getStartDate());
        bookingResponse.setEnd(booking.getEndDate());
        bookingResponse.setStatus(booking.getStatus());
        bookingResponse.setItem(itemMapper.toResponse(booking.getItem()));
        bookingResponse.setBooker(userMapper.toResponse(booking.getBooker()));
        return bookingResponse;
    }

    public List<BookingResponseDto> toResponseBooking(List<Booking> bookings) {
        return bookings.stream()
                .map(this::toResponseBooking)
                .toList();
    }
}
