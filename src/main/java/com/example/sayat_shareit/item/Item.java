package com.example.sayat_shareit.item;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Boolean available;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "item")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "item")
    private List<Comment> comments;
}
