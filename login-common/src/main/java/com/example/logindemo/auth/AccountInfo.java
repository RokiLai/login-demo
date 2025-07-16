package com.example.logindemo.auth;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {
    private Long id;
    private String username;
    private List<String> role;
    private List<String> permissions;
}
