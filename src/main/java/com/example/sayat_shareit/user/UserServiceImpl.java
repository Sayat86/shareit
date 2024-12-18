package com.example.sayat_shareit.user;

import com.example.sayat_shareit.user.dto.UserMapper;
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
        Optional<User> optional = userRepository.finsByEmail(user.getEmail());
        if (optional.isPresent()) {
            throw new RuntimeException("Пользователь с такой почтой уже существует");
        }
        return userRepository.create(user);
    }

    @Override
    public User update(User updatedUser, int id) {
        User existingUser = findById(id);
        userMapper.merge(existingUser, updatedUser);
        return userRepository.update(existingUser, id);
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
