package com.example.sayat_shareit.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int uniqueId = 1;

    @Override
    public User create(User user) {
        user.setId(uniqueId++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user, int id) {
        users.put(id, user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        User user = users.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteById(int id) {
        users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
