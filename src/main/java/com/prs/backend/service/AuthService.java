package com.prs.backend.service;

import com.prs.backend.dto.LoginRequest;
import com.prs.backend.dto.RegisterRequest;
import com.prs.backend.entity.User;
import com.prs.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // Hash password before saving
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        user.setRoleID(request.getRoleID());

        return userRepository.save(user);
    }

    public Map<String, Object> login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        Map<String, Object> response = new HashMap<>();

        if (userOptional.isEmpty()) {
            response.put("message", "User not found");
            return response;
        }

        User user = userOptional.get();

        // Compare raw password with hashed password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            response.put("message", "Invalid password");
            return response;
        }

        response.put("message", "Login successful");
        response.put("userID", user.getUserID());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());
        response.put("roleID", user.getRoleID());

        return response;
    }
}