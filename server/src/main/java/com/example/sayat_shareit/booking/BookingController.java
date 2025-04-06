package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import com.example.sayat_shareit.booking.dto.BookingMapper;
import com.example.sayat_shareit.booking.dto.BookingResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sayat_shareit.utils.RequestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public BookingResponseDto create(@Valid @RequestBody BookingCreateDto bookingCreate,
                                     @RequestHeader(USER_HEADER) int bookerId) {
        Booking booking = bookingMapper.fromCreate(bookingCreate);
        return bookingMapper.toResponseBooking(bookingService.create(booking, bookerId));
    }

    @PatchMapping("/{bookingId}")
    public BookingResponseDto update(@PathVariable int bookingId,
                                     @RequestHeader(USER_HEADER) int userId,
                                     @RequestParam boolean approved) {
        return bookingMapper.toResponseBooking(bookingService.update(bookingId, userId, approved));
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDto findById(@PathVariable int bookingId) {
        return bookingMapper.toResponseBooking(bookingService.findById(bookingId));
    }

    @GetMapping
    public List<BookingResponseDto> findAllByBookerId(@RequestHeader(USER_HEADER) int bookerId,
                                                      @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                      @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = from / size;
        return bookingMapper.toResponseBooking(bookingService.findAllByBookerId(bookerId, page, size));
    }

    @GetMapping("/owner")
    public List<BookingResponseDto> findByItemOwnerId(@RequestHeader(USER_HEADER) int ownerId,
                                                      @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                      @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        int page = from / size;
        return bookingMapper.toResponseBooking(bookingService.findByItemOwnerId(ownerId, page, size));
    }
}
