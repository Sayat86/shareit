package com.example.sayat_shareit.item_request;

import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "item_requests")
@Setter
@Getter
public class ItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String description;

    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private User requestor;

    @OneToMany(mappedBy = "request")
    private List<Item> items;
}
