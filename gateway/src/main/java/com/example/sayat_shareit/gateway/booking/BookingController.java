package com.example.sayat_shareit.gateway.booking;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingClient bookingClient;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return bookingClient.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return bookingClient.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BookingCreateDto bookingCreateDto) {
        return bookingClient.create(bookingCreateDto);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> update(@PathVariable int bookingId, @RequestHeader int userId, @RequestParam boolean approved) {
        return bookingClient.update(bookingId, userId, approved);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> findByItemOwnerId(@PathVariable int ownerId, int from, int size) {
        return bookingClient.findByItemOwnerId(ownerId, from, size);
    }
}
