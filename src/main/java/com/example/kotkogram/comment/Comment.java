package com.example.kotkogram.comment;

import java.time.LocalDate;

import com.example.kotkogram.post.Post;
import com.example.kotkogram.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    private Long id;

    @NotNull
    @Size(min = 1)
    private String content;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Comment() {
    }

    public Comment(Long id, String content, LocalDate createdAt, LocalDate updatedAt, Post post, User author) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
        this.author = author;
    }

    public Comment(String content, LocalDate createdAt, LocalDate updatedAt, Post post, User author) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
        this.author = author;
    }
}
