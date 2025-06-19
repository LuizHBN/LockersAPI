package com.inertia.lockersapi.api.controller;


import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import com.inertia.lockersapi.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid NewUserDTO UserDTO) {
        return userService.saveUser(UserDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.findAllUsers();
    }


}
