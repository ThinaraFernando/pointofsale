package com.ijse.pointofsale.service;


import org.springframework.stereotype.Service;

import com.ijse.pointofsale.entity.User;

@Service
public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

}
