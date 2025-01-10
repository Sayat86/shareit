package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookerId(int bookerId);

    List<Booking> findByItemOwnerId(int ownerId);
}
