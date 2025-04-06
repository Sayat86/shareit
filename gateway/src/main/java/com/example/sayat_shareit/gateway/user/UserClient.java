package com.example.sayat_shareit.gateway.user;

import com.example.sayat_shareit.gateway.common.BaseClient;
import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserUpdateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class UserClient extends BaseClient {
    public UserClient(@Value("${shareit.server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .build()
        );
    }

    public ResponseEntity<Object> findAll() {
        return get("/users");
    }

    public ResponseEntity<Object> findById(int id) {
        return get("/users/{id}", null, Map.of("id", id));
    }

    public ResponseEntity<Object> create(UserCreateDto userCreateDto) {
        return post("/users", userCreateDto);
    }

    public ResponseEntity<Object> update(UserUpdateDto userUpdateDto, int id) {
        return patch("/users/{id}", null, Map.of("id", id), userUpdateDto);
    }

    public ResponseEntity<Object> deleteById(int id) {
        return delete("/users/{id}", null, Map.of("id", id));
    }
}
