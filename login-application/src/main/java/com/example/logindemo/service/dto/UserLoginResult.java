package com.example.logindemo.service.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserLoginResult {
    private Long id;
    private String username;
    private String email; // 新增字段

}