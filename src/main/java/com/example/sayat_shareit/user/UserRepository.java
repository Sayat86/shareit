package com.example.sayat_shareit.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);

    User update(User user, int id);

    Optional<User> findById(int id);

    void deleteById(int id);

    List<User> findAll();

    Optional<User> finsByEmail(String email);
}
