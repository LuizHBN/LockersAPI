package com.inertia.lockersapi.domain.user;

import com.inertia.lockersapi.api.controller.dto.request.user.NewUserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
