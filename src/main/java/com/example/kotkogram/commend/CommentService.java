package com.example.kotkogram.commend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment addComment(Comment comment) {
        Optional<Comment> commentById = commentRepository.findById(comment.getId());

        if (commentById.isPresent()) {
            throw new IllegalStateException("Comment with id " + comment.getId() + " already exists");
        }

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long id, Comment comment) {
        return commentRepository.findById(id).map(c -> {
            c.setContent(comment.getContent());
            c.setUpdatedAt(comment.getUpdatedAt());
            return commentRepository.save(c);
        }).orElseThrow(() -> new IllegalStateException("Comment with id " + id + " does not exist"));
    }

    public void deleteComment(Long id) {
        boolean exists = commentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Comment with id " + id + " does not exist");
        }

        commentRepository.deleteById(id);
    }

}
