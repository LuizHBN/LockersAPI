package com.inertia.lockersapi.api.controller;


import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import com.inertia.lockersapi.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(NewUserDTO newUserDTO) {
        return userService.saveUser(newUserDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.findAllUsers();
    }


}
