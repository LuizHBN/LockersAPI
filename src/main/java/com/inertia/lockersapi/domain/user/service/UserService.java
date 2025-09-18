package com.inertia.lockersapi.domain.user.service;

import com.inertia.lockersapi.api.controller.dto.request.user.LoginUserDTO;
import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadUserDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadUserLoginDTO;
import com.inertia.lockersapi.domain.user.User;
import com.inertia.lockersapi.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> saveUser(NewUserDTO userDTO) {
        var encryptPassword = passwordEncoder.encode(userDTO.password());

        User user = new User(userDTO, encryptPassword);

        userRepository.save(user);

        return ResponseEntity.ok(new ReadUserDTO(user));
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public ResponseEntity<?> validateLogin(LoginUserDTO loginUserDTO) {
        Optional<User> userOpt = findByEmail(loginUserDTO.email());

        if (userOpt.isEmpty()) {
            // Email não cadastrado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }

        User user = userOpt.get();

        // Verifica a senha
        if (!user.getPassword().equals(loginUserDTO.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }

        // Login válido
        return ResponseEntity.ok(new ReadUserLoginDTO(user));
    }


    public ResponseEntity<?> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<?> findUserById(UUID id) {
        Optional<User> users = userRepository.findById(id);
        return ResponseEntity.ok(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
