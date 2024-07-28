package com.example.kotkogram.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDto userData) {
        User existing = userRepository.findByUsername(userData.getUsername());

        if (existing != null) {
            throw new IllegalStateException("User with username " + userData.getUsername() + " already exists");
        }

        User newUser = new User();
        newUser.setUsername(userData.getUsername());

        newUser.setPassword(userData.getPassword());

        newUser.setCreatedAt(LocalDate.now());
        newUser.setUpdatedAt(LocalDate.now());

        return userRepository.save(newUser);
    }

}
