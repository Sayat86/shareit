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

    // для коммента
    List<Booking> findByItemAndBookerAndStatusAndStartDateIsBefore(Item item, User booker, BookingStatus status,
                                                                   LocalDateTime startDate);


    List<Booking> findAllByBooker_IdOrderByStartDateDesc(Integer bookerId, Pageable pageable);

    List<Booking> findAllByBooker_IdAndEndDateBeforeOrderByStartDateDesc(Integer bookerId, LocalDateTime endDate, Pageable pageable);

    List<Booking> findAllByBooker_IdAndStartDateAfterOrderByStartDateDesc(Integer bookerId, LocalDateTime startDate, Pageable pageable);

    List<Booking> findAllByBooker_IdAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(
            Integer bookerId, LocalDateTime now1, LocalDateTime now2, Pageable pageable);

    List<Booking> findAllByBooker_IdAndStatusOrderByStartDateDesc(Integer bookerId, BookingStatus status, Pageable pageable);


    List<Booking> findAllByItem_Owner_IdOrderByStartDateDesc(Integer ownerId, Pageable pageable);

    List<Booking> findAllByItem_Owner_IdAndEndDateBeforeOrderByStartDateDesc(Integer ownerId, LocalDateTime endDate, Pageable pageable);

    List<Booking> findAllByItem_Owner_IdAndStartDateAfterOrderByStartDateDesc(Integer ownerId, LocalDateTime startDate, Pageable pageable);

    List<Booking> findAllByItem_Owner_IdAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(
            Integer ownerId, LocalDateTime now1, LocalDateTime now2, Pageable pageable);

    List<Booking> findAllByItem_Owner_IdAndStatusOrderByStartDateDesc(Integer ownerId, BookingStatus status, Pageable pageable);

}
