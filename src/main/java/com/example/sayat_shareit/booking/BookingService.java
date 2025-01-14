package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;

import java.util.List;

public interface BookingService {
    Booking create(Booking booking, int bookerId);

    Booking update(int bookingId, int userId, boolean approved);

    Booking findById(int id);

    List<Booking> findAllByBookerId(int bookerId);

    List<Booking> findByItemOwnerId(int ownerId);
}
