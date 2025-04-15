package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.booking.dto.BookingMapper;
import com.example.sayat_shareit.exception.BadRequestException;
import com.example.sayat_shareit.exception.ForbiddenException;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.ItemRepository;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public Booking create(Booking booking, int bookerId) {
        if (!booking.getEndDate().isAfter(booking.getStartDate())) {
            throw new BadRequestException("Дата окончания должна быть после даты начала бронирования");
        }

        User user = userRepository.findById(bookerId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        Item item = itemRepository.findById(booking.getItem().getId())
                .orElseThrow(() -> new NotFoundException("Вещь с таким ID не найдена"));
        if (!item.getAvailable()) {
            throw new BadRequestException("Предмет не доступен для бронирования");
        }
        booking.setItem(item);
        booking.setBooker(user);
        booking.setStatus(BookingStatus.WAITING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(int bookingId, int userId, boolean approved) {
        Booking bookingExisting = findById(bookingId);
        Item item = bookingExisting.getItem();
        if (item.getOwner().getId() != userId) {
            throw new ForbiddenException("Пользователь с ID = " + userId +
                    " не является владельцем предмета с ID " + item.getId());
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (approved) {
            bookingExisting.setStatus(BookingStatus.APPROVED);
        }
        else {
            bookingExisting.setStatus(BookingStatus.REJECTED);
        }
        return bookingRepository.save(bookingExisting);
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Бронирование пользователя не найдено"));
    }

    @Override
    public List<Booking> findAllByBookerId(int bookerId, int page, int size, BookingState state) {
        userRepository.findById(bookerId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Pageable pageable = PageRequest.of(page, size);
        switch (state) {
            case PAST -> {
                return bookingRepository.findAllByBooker_IdAndEndDateBeforeOrderByStartDateDesc(bookerId, LocalDateTime.now(), pageable);
            }
            case FUTURE -> {
                return bookingRepository.findAllByBooker_IdAndStartDateAfterOrderByStartDateDesc(bookerId, LocalDateTime.now(), pageable);
            }
            case CURRENT -> {
                return bookingRepository.findAllByBooker_IdAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(
                        bookerId, LocalDateTime.now(), LocalDateTime.now(), pageable);
            }
            case WAITING -> {
                return bookingRepository.findAllByBooker_IdAndStatusOrderByStartDateDesc(bookerId, BookingStatus.WAITING, pageable);
            }
            case REJECTED -> {
                return bookingRepository.findAllByBooker_IdAndStatusOrderByStartDateDesc(bookerId, BookingStatus.REJECTED, pageable);
            }
            default -> { // ALL
                return bookingRepository.findAllByBooker_IdOrderByStartDateDesc(bookerId, pageable);
            }
        }
    }

    @Override
    public List<Booking> findByItemOwnerId(int ownerId, int page, int size, BookingState state) {
        userRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        Pageable pageable = PageRequest.of(page, size);

        switch (state) {
            case PAST -> {
                return bookingRepository.findAllByItem_Owner_IdAndEndDateBeforeOrderByStartDateDesc(ownerId, LocalDateTime.now(), pageable);
            }
            case FUTURE -> {
                return bookingRepository.findAllByItem_Owner_IdAndStartDateAfterOrderByStartDateDesc(ownerId, LocalDateTime.now(), pageable);
            }
            case CURRENT -> {
                return bookingRepository.findAllByItem_Owner_IdAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(
                        ownerId, LocalDateTime.now(), LocalDateTime.now(), pageable);
            }
            case WAITING -> {
                return bookingRepository.findAllByItem_Owner_IdAndStatusOrderByStartDateDesc(ownerId, BookingStatus.WAITING, pageable);
            }
            case REJECTED -> {
                return bookingRepository.findAllByItem_Owner_IdAndStatusOrderByStartDateDesc(ownerId, BookingStatus.REJECTED, pageable);
            }
            default -> { // ALL
                return bookingRepository.findAllByItem_Owner_IdOrderByStartDateDesc(ownerId, pageable);
            }
        }
    }
}
