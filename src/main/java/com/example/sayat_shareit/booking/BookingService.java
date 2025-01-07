package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;

import java.util.List;

public interface BookingService {
    Booking create(Booking booking, int bookingId);

    Booking update(Booking booking, int bookingId, int itemId);

    Booking findById(int id);

    List<Booking> findAll();

    List<Booking> findAllByItemId(int itemId);

    List<Booking> searchOwner(String text);
}
