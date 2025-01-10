package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.booking.dto.BookingMapper;
import com.example.sayat_shareit.exception.ForbiddenException;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.ItemRepository;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking create(Booking booking) {
        Item item = itemRepository.findById(booking.getItem().getId())
                .orElseThrow(() -> new NotFoundException("Вещь с таким ID не найдена"));
        booking.setItem(item);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(int bookingId, int userId, boolean approved) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Booking bookingExisting = findById(bookingId);
        Item item = bookingExisting.getItem();
        if (item.getOwner().getId() != userId) {
            throw new ForbiddenException("Пользователь с ID = " + userId +
                    " не является владельцем предмета с ID " + item.getId());
        }
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
    public List<Booking> findAllByBookerId(int bookerId) {
        return bookingRepository.findByBookerId(bookerId);
    }

    @Override
    public List<Booking> findByItemOwnerId(int ownerId) {
        userRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return bookingRepository.findByItemOwnerId(ownerId);
    }
}
