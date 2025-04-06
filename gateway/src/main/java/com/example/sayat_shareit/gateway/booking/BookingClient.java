package com.example.sayat_shareit.gateway.booking;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import com.example.sayat_shareit.gateway.common.BaseClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class BookingClient extends BaseClient {
    public BookingClient(@Value("${shareit.server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .build()
        );
    }

    public ResponseEntity<Object> findAll() {
        return get("/bookings");
    }

    public ResponseEntity<Object> findById(Long id) {
        return get("/bookings/{id}", null, Map.of("id", id));
    }

    public ResponseEntity<Object> create(BookingCreateDto bookingCreateDto) {
        return post("/bookings", bookingCreateDto);
    }

    public ResponseEntity<Object> update(int bookingId, int userId, boolean approved) {
        return patch("/bookings/{bookingId}", userId, approved);
    }

    public ResponseEntity<Object> findByItemOwnerId(int ownerId, int from, int size) {
        return get("owner", ownerId, Map.of("from", from, "size", size));
    }
}
