package com.example.sayat_shareit.user;

import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user, int id);

    User findById(int id);

    void deleteById(int id);

    List<User> findAll();
}
