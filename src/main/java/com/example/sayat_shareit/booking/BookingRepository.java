package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookerId(int bookerId);

    List<Booking> findByItemOwnerId(int ownerId);

    List<Booking> findByItemAndBookerAndStatusAndStartDateIsBefore(Item item, User booker, BookingStatus status,
                                                                   LocalDateTime startDate);
}
