package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        return ResponseEntity
                .ok(userService.findAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
        return ResponseEntity
                .ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserById(@PathVariable Long id,
                                             @Valid @RequestBody User newUser){
        return ResponseEntity
                .ok(userService.editUserById(id, newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
