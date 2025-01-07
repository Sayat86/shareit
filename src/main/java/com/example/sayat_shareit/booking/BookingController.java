package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import com.example.sayat_shareit.booking.dto.BookingMapper;
import com.example.sayat_shareit.booking.dto.BookingResponseDto;
import com.example.sayat_shareit.booking.dto.BookingUpdateDto;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemResponseDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.USER_HEADER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public BookingResponseDto create(@Valid @RequestBody BookingCreateDto bookingCreate,
                                     @RequestHeader int bookingId) {
        Booking booking = bookingMapper.fromCreate(bookingCreate);
        return bookingMapper.toResponseBooking(bookingService.create(booking, bookingId));
    }

    @PatchMapping("/{bookingId}")
    public BookingResponseDto update(@RequestBody BookingUpdateDto bookingUpdate,
                                     @PathVariable int bookingId,
                                     @RequestHeader int itemId) {
        Booking booking = bookingMapper.fromUpdate(bookingUpdate);
        return bookingMapper.toResponseBooking(bookingService.update(booking, bookingId, itemId));
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDto findById(@PathVariable int bookingId) {
        return bookingMapper.toResponseBooking(bookingService.findById(bookingId));
    }

    @GetMapping
    public List<BookingResponseDto> findAllByItemId(@RequestHeader int itemId) {
        return bookingMapper.toResponseBooking(bookingService.findAllByItemId(itemId));
    }

    @GetMapping("/owner")
    public List<BookingResponseDto> searchOwner(@RequestParam String text) {
        return bookingMapper.toResponseBooking(bookingService.searchOwner(text));
    }
}
