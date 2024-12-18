package com.example.sayat_shareit.user;

import com.example.sayat_shareit.user.dto.UserCreateDto;
import com.example.sayat_shareit.user.dto.UserMapper;
import com.example.sayat_shareit.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponseDto create(@RequestBody UserCreateDto userCreate) {
        User user = userMapper.fromCreate(userCreate);
        return userMapper.toResponse(userService.create(user));
    }

    //@PatchMapping

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable int id) {
        return userMapper.toResponse(userService.findById(id));
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userMapper.toResponse(userService.findAll());
    }
}
