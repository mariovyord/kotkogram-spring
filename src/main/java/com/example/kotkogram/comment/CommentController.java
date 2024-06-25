package com.example.kotkogram.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public List<CommentDto> getCommentsByPostId(@RequestParam Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping(path = "{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public CommentDto addComment(@RequestBody CreateCommentDto comment) {
        return commentService.addComment(comment);
    }

    @PutMapping(path = "{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping(path = "{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

}
