package com.example.kotkogram.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public CreateUserDto() {
    }

    public CreateUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}