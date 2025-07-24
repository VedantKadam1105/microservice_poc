package com.userservice.UserService.services;

import com.userservice.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void deleteUser(String userId);

    User updateUser(String userId,User updatedUser);
}
