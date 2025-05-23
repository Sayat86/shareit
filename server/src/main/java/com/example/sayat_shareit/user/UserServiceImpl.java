package com.example.sayat_shareit.user;

import com.example.sayat_shareit.exception.ConflictException;
import com.example.sayat_shareit.user.dto.UserMapper;
import com.example.sayat_shareit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User updatedUser, int id) {
        Optional<User> optionalEmail = userRepository.findByEmail(updatedUser.getEmail());

        if (optionalEmail.isPresent()) {
            User foundUserByEmail = optionalEmail.get();
            if (foundUserByEmail.getId() != id) {
                throw new ConflictException("Пользователь с такой почтой уже существует");
            }
        }
        User existingUser = findById(id);
        userMapper.merge(existingUser, updatedUser);
        return userRepository.save(existingUser);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Пользователь с таким ID не найден"));
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
