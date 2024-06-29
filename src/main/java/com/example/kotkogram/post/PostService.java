package com.example.kotkogram.post;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.example.kotkogram.user.User;
import com.example.kotkogram.user.UserDto;
import com.example.kotkogram.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByAuthorId(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + " does not exist"));
    }

    public PostDto addPost(CreatePostDto post) {
        Post newPost = new Post();
        newPost.setImageUrl(post.getImageUrl());
        newPost.setDescription(post.getDescription());
        newPost.setCreatedAt(LocalDate.now());
        newPost.setUpdatedAt(LocalDate.now());

        User author = userRepository.findById(post.getAuthorId())
                .orElseThrow(() -> new IllegalStateException("User with id " + post.getAuthorId() + " does not exist"));

        newPost.setAuthor(author);

        Post createdPost = postRepository.save(newPost);

        PostDto postDto = new PostDto();

        postDto.setId(createdPost.getId());
        postDto.setImageUrl(createdPost.getImageUrl());
        postDto.setDescription(createdPost.getDescription());
        postDto.setCreatedAt(createdPost.getCreatedAt());
        postDto.setUpdatedAt(createdPost.getUpdatedAt());

        UserDto authorDto = new UserDto();
        authorDto.setId(author.getId());
        authorDto.setUsername(author.getUsername());
        authorDto.setCreatedAt(author.getCreatedAt());
        authorDto.setUpdatedAt(author.getUpdatedAt());

        postDto.setAuthor(authorDto);

        return postDto;

    }

    @Transactional
    public Post updatePost(Long id, Post post) {
        return postRepository.findById(id).map(p -> {
            p.setImageUrl(post.getImageUrl());
            p.setDescription(post.getDescription());
            p.setUpdatedAt(post.getUpdatedAt());
            return postRepository.save(p);
        }).orElseThrow(() -> new IllegalStateException("Post with id " + id + " does not exist"));
    }

    public void deletePost(Long id) {
        boolean exists = postRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Post with id " + id + " does not exist");
        }

        postRepository.deleteById(id);
    }
}
