package com.example.sayat_shareit.gateway.user;

import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserClient userClient;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return userClient.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        return userClient.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserCreateDto userCreateDto) {
        return userClient.create(userCreateDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UserUpdateDto userUpdateDto, @PathVariable int id) {
        return userClient.update(userUpdateDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        return userClient.deleteById(id);
    }
}
