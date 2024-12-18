package com.example.sayat_shareit.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private int id;
    private String name;
    private String description;
    private boolean available;
    private String owner;
}
