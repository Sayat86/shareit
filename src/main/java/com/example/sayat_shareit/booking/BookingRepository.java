package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Page<Booking> findByBookerId(int bookerId, Pageable pageable);

    Page<Booking> findByItemOwnerId(int ownerId, Pageable pageable);

    List<Booking> findByItemAndBookerAndStatusAndStartDateIsBefore(Item item, User booker, BookingStatus status,
                                                                   LocalDateTime startDate);
}
