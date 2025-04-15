package com.example.sayat_shareit.gateway.booking;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.sayat_shareit.utils.RequestConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingClient bookingClient;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestHeader(USER_HEADER) int bookerId,
                                          @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                          @RequestParam(defaultValue = DEFAULT_SIZE) int size,
                                          @RequestParam(defaultValue = "ALL") String state) {
        return bookingClient.findAll(bookerId, from, size, state);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return bookingClient.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody BookingCreateDto bookingCreate,
                                         @RequestHeader(USER_HEADER) int bookerId) {
        return bookingClient.create(bookingCreate, bookerId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> update(@PathVariable int bookingId,
                                         @RequestHeader(USER_HEADER) int userId,
                                         @RequestParam boolean approved) {
        return bookingClient.update(bookingId, userId, approved);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> findByItemOwnerId (@RequestHeader(USER_HEADER) int ownerId,
                                                     @RequestParam(defaultValue = DEFAULT_FROM) int from,
                                                     @RequestParam(defaultValue = DEFAULT_SIZE) int size,
                                                     @RequestParam(defaultValue = "ALL") String state) {
        return bookingClient.findByItemOwnerId(ownerId, from, size, state);
    }
}
