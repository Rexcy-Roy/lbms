package com.lbms.lbms.service.impl;

import com.lbms.lbms.entity.Book;
import com.lbms.lbms.entity.User;
import com.lbms.lbms.exception.BookNotFoundException;
import com.lbms.lbms.exception.UserAlreadyExistsException;
import com.lbms.lbms.repository.UserRepository;
import com.lbms.lbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserAlreadyExistsException("User with the same email already exists"));
        return userRepository.save(user);
    }





}
