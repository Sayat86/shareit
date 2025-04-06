package com.example.sayat_shareit.item;

import com.example.sayat_shareit.booking.Booking;
import com.example.sayat_shareit.booking.BookingRepository;
import com.example.sayat_shareit.booking.BookingStatus;
import com.example.sayat_shareit.exception.NotFoundException;
import com.example.sayat_shareit.user.User;
import com.example.sayat_shareit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Comment create(Comment comment, int itemId, int userId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь с таким ID не найдена"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        Booking booking = bookingRepository.findByItemAndBookerAndStatusAndStartDateIsBefore(item, user,
                        BookingStatus.APPROVED, LocalDateTime.now().plusSeconds(3))
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException("Данный пользователь не бронировал данный предмет"));
        comment.setItem(item);
        comment.setAuthor(user);
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}
