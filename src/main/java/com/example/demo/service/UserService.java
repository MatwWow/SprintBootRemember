package com.example.demo.service;

import com.example.demo.exception.NotFindIdException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(NotFindIdException::new);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
