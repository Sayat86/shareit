package com.example.sayat_shareit.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Item {
    private int id;
    private String name;
    private String description;
    private boolean available;
    private String owner;
}
