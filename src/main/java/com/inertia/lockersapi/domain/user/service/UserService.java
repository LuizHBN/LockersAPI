package com.inertia.lockersapi.domain.user.service;

import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadUserDTO;
import com.inertia.lockersapi.domain.user.User;
import com.inertia.lockersapi.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> saveUser(NewUserDTO userDTO) {
        User user = new User(userDTO);
        userRepository.save(user);
        return ResponseEntity.ok(new ReadUserDTO(user));
    }

    public ResponseEntity<?> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<?> findUserById(UUID id) {
        Optional<User> users = userRepository.findById(id);
        return ResponseEntity.ok(users);
    }
}
