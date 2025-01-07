package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByItemId(int itemId);

    List<Booking> searchOwner(String text);
}
