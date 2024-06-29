package com.example.kotkogram.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDto {

    @NotNull
    @Size(min = 1)
    private String content;

    @NotNull
    private Long postId;

    @NotNull
    private Long authorId;

    public CreateCommentDto() {
    }

    public CreateCommentDto(String content, Long postId, Long authorId) {
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
    }
}
