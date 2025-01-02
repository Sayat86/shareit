package com.example.sayat_shareit.user;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.comment.Comment;
import com.example.sayat_shareit.item.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Item> items;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
