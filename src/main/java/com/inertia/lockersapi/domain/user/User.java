package com.inertia.lockersapi.domain.user;

import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String cpf;

    public User(NewUserDTO newUserDTO) {
        this.name = newUserDTO.name();
        this.lastName = newUserDTO.lastName();
        this.email = newUserDTO.email();
        this.password = newUserDTO.password();
        this.phone = newUserDTO.phone();
        this.cpf = newUserDTO.cpf();
    }

}
