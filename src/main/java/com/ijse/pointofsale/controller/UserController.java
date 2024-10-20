package com.ijse.pointofsale.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.ijse.pointofsale.entity.User;
import com.ijse.pointofsale.service.UserService;

@RestController
@CrossOrigin(origins ="*")
public class UserController {


    @Autowired
    private UserService userService;
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
