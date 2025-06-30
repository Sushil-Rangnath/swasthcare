package com.swasthcare.swasthcare.dto;

import com.swasthcare.swasthcare.entity.Role;
import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Role role;  // USER or ADMIN
}
