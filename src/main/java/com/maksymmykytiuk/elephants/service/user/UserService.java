package com.maksymmykytiuk.elephants.service.user;

import com.maksymmykytiuk.elephants.model.user.User;

public interface UserService {
    User findUserByUsername(String username);

    void save(User user);

    boolean validEmail(String email);

    User findUserByEmail(String email);
}