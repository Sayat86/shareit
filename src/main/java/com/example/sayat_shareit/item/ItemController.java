package com.example.sayat_shareit.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @GetMapping("/items")
    public void test(@RequestHeader("X-Sharer-User-Id") int userId) {
        System.out.println(userId);
    }
}
