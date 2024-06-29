package com.example.kotkogram.comment;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kotkogram.post.Post;
import com.example.kotkogram.post.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(c -> new CommentDto(
                c.getId(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt())).toList();
    }

    public CommentDto getCommentById(Long id) {
        return commentRepository.findById(id).map(c -> new CommentDto(
                c.getId(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt()))
                .orElseThrow(() -> new IllegalStateException("Comment with id " + id + " does not exist"));
    }

    public CommentDto addComment(CreateCommentDto commentDto) {
        Comment newComment = new Comment();
        newComment.setContent(commentDto.getContent());
        newComment.setCreatedAt(LocalDate.now());
        newComment.setUpdatedAt(LocalDate.now());

        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(
                        () -> new IllegalStateException("Post with id " + commentDto.getPostId() + " does not exist"));

        newComment.setPost(post);

        Comment createdComment = commentRepository.save(newComment);

        return new CommentDto(
                createdComment.getId(),
                createdComment.getContent(),
                createdComment.getCreatedAt(),
                createdComment.getUpdatedAt());

    }

    @Transactional
    public CommentDto updateComment(Long id, Comment comment) {
        return commentRepository.findById(id).map(c -> new CommentDto(
                c.getId(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt()))
                .orElseThrow(() -> new IllegalStateException("Comment with id " + id + " does not exist"));
    }

    public void deleteComment(Long id) {
        boolean exists = commentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Comment with id " + id + " does not exist");
        }

        commentRepository.deleteById(id);
    }

}
