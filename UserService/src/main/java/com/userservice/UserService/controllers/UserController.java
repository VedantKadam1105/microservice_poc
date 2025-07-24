package com.userservice.UserService.controllers;

import com.userservice.UserService.entities.User;
import com.userservice.UserService.payload.ApiResponse;
import com.userservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable(name = "id") String userId){
        User user=userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping
    public ResponseEntity<List<User>>getAllUser(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().success(true).message("deleted").build());

    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id,@RequestBody User user){
        User updatedUser=userService.updateUser(id,user);
        return ResponseEntity.ok().body(updatedUser);
    }
}
