package com.example.kotkogram.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody CreateUserDto user) {
        userService.createUser(user);
    }

    @PostMapping(path = "/login")
    public String loginUser(@RequestBody LoginUserDto user) {
        return userService.loginUser(user);
    }

}
