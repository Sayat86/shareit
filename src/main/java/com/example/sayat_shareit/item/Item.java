package com.example.sayat_shareit.item;

import com.example.sayat_shareit.user.User;
import lombok.Data;

@Data
public class Item {
    private int id;
    private String name;
    private String description;
    private Boolean available;
    private User owner;
}
