package com.example.kotkogram.comment;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
