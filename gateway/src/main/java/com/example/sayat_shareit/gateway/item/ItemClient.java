package com.example.sayat_shareit.gateway.item;

import com.example.sayat_shareit.gateway.common.BaseClient;
import com.example.sayat_shareit.item.dto.ItemCreateDto;
import com.example.sayat_shareit.item.dto.ItemUpdateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class ItemClient extends BaseClient {
    public ItemClient(@Value("${shareit.server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .build()
        );
    }

    public ResponseEntity<Object> findAllByOwnerId(int userId) {
        return get("/items", userId);
    }

    public ResponseEntity<Object> findById(Long id) {
        return get("/items/{id}", null, Map.of("id", id));
    }

    public ResponseEntity<Object> create(ItemCreateDto itemCreateDto, int userId) {
        return post("/items", userId, itemCreateDto);
    }

    public ResponseEntity<Object> update(ItemUpdateDto itemUpdateDto, int userId, int itemId) {
        return patch("/items/{id}", userId, Map.of("id", itemId), itemUpdateDto);
    }

    public ResponseEntity<Object> search(String text, int from, int size) {
        Map<String, Object> params = Map.of(
                "text", text,
                "from", from,
                "size", size
        );
        return get("/items/search?text={text}&from={from}&size={size}", null, params);
    }
}
