package com.example.kotkogram.post;

import java.time.LocalDate;

import com.example.kotkogram.user.User;
import com.example.kotkogram.user.UserDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    @NotNull
    private String imageUrl;

    @NotNull
    private String description;

    @NotNull
    private UserDto author;

    @NotNull
    private Long id;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

}
