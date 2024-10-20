package com.ijse.pointofsale.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.entity.User;
import com.ijse.pointofsale.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User createUser(User user) {
        //encode the password make it not human readable
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    

}

