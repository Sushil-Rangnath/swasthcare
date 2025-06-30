package com.swasthcare.swasthcare.controller;

import com.swasthcare.swasthcare.dto.AuthRequest;
import com.swasthcare.swasthcare.dto.AuthResponse;
import com.swasthcare.swasthcare.entity.User;
import com.swasthcare.swasthcare.repository.UserRepository;
import com.swasthcare.swasthcare.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        if (userRepo.findByEmail(request.getEmail()) != null) {
            return "User already exists!";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                request.getEmail(), request.getPassword(),
                userRepo.findByEmail(request.getEmail()).getRole() == null ?
                        null :
                        java.util.Collections.singleton(() -> userRepo.findByEmail(request.getEmail()).getRole().name()));

        final String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token, userRepo.findByEmail(request.getEmail()).getRole().name());
    }
}