package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.exception.NotFindIdException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> findAllUser(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(), user.getName(), user.getPassword()
                ))
                .toList();
    }

    public UserResponseDTO findUserById(Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(NotFindIdException::new);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getPassword());
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = new User(userRequestDTO.getName(), userRequestDTO.getPassword());
        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getPassword());
    }

    public UserResponseDTO editUserById(Long id, UserRequestDTO newUser){
        User user = userRepository
                .findById(id)
                .orElseThrow(NotFindIdException::new);

        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());

        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getName(), user.getPassword());
    }

    public void deleteUserById(Long id){
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()){
            userRepository.deleteById(id);
        }
    }

}
