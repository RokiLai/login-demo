package com.example.logindemo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public Account(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean matchPassword(String input) {
        return input != null && input.equals(this.password);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
