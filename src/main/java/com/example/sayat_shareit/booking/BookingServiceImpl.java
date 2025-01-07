package com.example.sayat_shareit.booking;

import com.example.sayat_shareit.booking.dto.BookingMapper;
import com.example.sayat_shareit.exception.ForbiddenException;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.item.Item;
import com.example.sayat_shareit.item.ItemRepository;
import com.example.sayat_shareit.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final BookingMapper bookingMapper;
    @Override
    public Booking create(Booking booking, int bookingId) {
        Item item = itemRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Вещь с таким ID не найдена"));
        booking.setItem(item);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking updateBooking, int bookingId, int itemId) {
        bookingRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь с таким ID не найдена"));
        Booking bookingExisting = findById(bookingId);
        if (bookingExisting.getItem().getId() != itemId) {
            throw new ForbiddenException("Данный предмет не забронирован");
        }
        bookingMapper.mergeBooking(bookingExisting, updateBooking);
        return bookingRepository.save(bookingExisting);
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Бронирование пользователя не найдено"));
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findAllByItemId(int itemId) {
        return bookingRepository.findByItemId(itemId);
    }

    @Override
    public List<Booking> searchOwner(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        return bookingRepository.searchOwner(text);
    }
}
