package com.example.kotkogram.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {

    @NotNull
    @Size(min = 4)
    private String imageUrl;

    @NotNull
    @Size(min = 4)
    private String description;

    @NotNull
    private Long authorId;

}
