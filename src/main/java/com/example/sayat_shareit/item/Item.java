package com.example.sayat_shareit.item;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.comment.Comment;
import com.example.sayat_shareit.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Boolean available;
    private User owner;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToMany(mappedBy = "item")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "item")
    private List<Comment> comments;
}
