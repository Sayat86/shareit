package com.example.sayat_shareit.gateway.item_request;

import com.example.sayat_shareit.booking.dto.BookingCreateDto;
import com.example.sayat_shareit.gateway.common.BaseClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class ItemRequestClient extends BaseClient {
    public ItemRequestClient(@Value("${shareit.server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .build()
        );
    }

    public ResponseEntity<Object> findAll() {
        return get("/requests");
    }

    public ResponseEntity<Object> findById(Long id) {
        return get("/requests/{id}", null, Map.of("id", id));
    }

    public ResponseEntity<Object> create(BookingCreateDto bookingCreateDto) {
        return post("/requests", bookingCreateDto);
    }
}
