package com.example.sayat_shareit.gateway.item_request;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class ItemRequestController {
    private final ItemRequestClient itemRequestClient;

    @GetMapping
    public ResponseEntity<Object> findAll() {return itemRequestClient.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {return itemRequestClient.findById(id);}

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BookingCreateDto bookingCreateDto) {
        return itemRequestClient.create(bookingCreateDto);}
}
