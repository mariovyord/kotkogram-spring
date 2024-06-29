package com.example.kotkogram.user;

import java.time.LocalDate;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDto userData) {
        User existing = userRepository.findByUsername(userData.getUsername());

        if (existing != null) {
            throw new IllegalStateException("User with username " + userData.getUsername() + " already exists");
        }

        User newUser = new User();
        newUser.setUsername(userData.getUsername());

        String hashedPassword = passwordEncoder.encode(userData.getPassword());
        newUser.setPassword(hashedPassword);

        newUser.setCreatedAt(LocalDate.now());
        newUser.setUpdatedAt(LocalDate.now());

        return userRepository.save(newUser);
    }

    public String loginUser(LoginUserDto user) {
        User existing = userRepository.findByUsername(user.getUsername());

        if (existing == null || !passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            throw new IllegalStateException("Invalid username or password");
        }

        return Jwts.builder()
                .setSubject(existing.getUsername())
                .setIssuedAt(new Date())
                .compact();
    }

}
