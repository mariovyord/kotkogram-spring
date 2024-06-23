package com.example.kotkogram.posts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + " does not exist"));
    }

    public Post addPost(Post post) {
        Optional<Post> postById = postRepository.findById(post.getId());

        if (postById.isPresent()) {
            throw new IllegalStateException("Post with id " + post.getId() + " already exists");
        }

        return postRepository.save(post);
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
