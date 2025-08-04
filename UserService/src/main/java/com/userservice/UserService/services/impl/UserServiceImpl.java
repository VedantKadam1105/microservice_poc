package com.userservice.UserService.services.impl;

import com.userservice.UserService.entities.Rating;
import com.userservice.UserService.entities.User;
import com.userservice.UserService.exceptions.ResourceNotFoundException;
import com.userservice.UserService.repositories.UserRepository;
import com.userservice.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
       String userId= UUID.randomUUID().toString();
       user.setUserId(userId);
       return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        List<User> userList= userRepository.findAll();
        userList.stream().forEach((user)->user.setRatings(restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), ArrayList.class)));
        return  userList;
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id is not found on server"));
       ArrayList<Rating> forObject= restTemplate.getForObject("http://localhost:8083/ratings/user/"+userId, ArrayList.class);
       logger.info("{}",forObject);
       user.setRatings(forObject);
        return user;
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
