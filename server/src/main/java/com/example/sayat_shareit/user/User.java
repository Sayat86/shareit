package com.example.sayat_shareit.user;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.item.Comment;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item_request.ItemRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    @OneToMany(mappedBy = "booker")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "requestor")
    private List<ItemRequest> itemRequests;
}
