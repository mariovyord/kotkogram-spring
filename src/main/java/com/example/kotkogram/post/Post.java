package com.example.kotkogram.post;

import java.time.LocalDate;

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
public class Post {

    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long id;

    @NotNull
    @Size(min = 4)
    private String imageUrl;

    @NotNull
    @Size(min = 4)
    private String description;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Post() {
    }

    public Post(Long id, String imageUrl, String description, LocalDate createdAt, LocalDate updatedAt, User author) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public Post(String imageUrl, String description, LocalDate createdAt, LocalDate updatedAt, User author) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }
}
