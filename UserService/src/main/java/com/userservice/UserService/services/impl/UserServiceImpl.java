package com.userservice.UserService.services.impl;

import com.userservice.UserService.entities.User;
import com.userservice.UserService.exceptions.ResourceNotFoundException;
import com.userservice.UserService.repositories.UserRepository;
import com.userservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
       String userId= UUID.randomUUID().toString();
       user.setUserId(userId);
       return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id is not found on server"));
    }

    @Override
    public void deleteUser(String userId) {
         userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id is not found on server"));
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setAbout(updatedUser.getAbout());
        return userRepository.save(user);
    }
}
