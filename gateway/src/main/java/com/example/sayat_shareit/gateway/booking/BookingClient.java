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

    public ResponseEntity<Object> findAll(int bookerId, int from, int size) {
        Map<String, Object> params = Map.of("from", from,
                "size", size);
        return get("/bookings", bookerId, params);
    }

    public ResponseEntity<Object> findById(Long id) {
        return get("/bookings/{id}", null, Map.of("id", id));
    }

    public ResponseEntity<Object> create(BookingCreateDto bookingCreateDto, int bookerId) {
        return post("/bookings", bookerId, bookingCreateDto);
    }

    public ResponseEntity<Object> update(int bookingId, int userId, boolean approved) {
        Map<String, Object> vars = Map.of(
                "bookingId", bookingId,
                "approved", approved
        );
        return patch("/bookings/{bookingId}?approved={approved}", userId, vars, null);
    }

    public ResponseEntity<Object> findByItemOwnerId(int ownerId, int from, int size) {
        Map<String, Object> params = Map.of("from", from,
                "size", size);
        return get("/bookings", ownerId, params);
    }
}
