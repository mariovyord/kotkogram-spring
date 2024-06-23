package com.example.kotkogram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/post")
public class PostController {

    private final PostService postsService;

    @Autowired
    public PostController(PostService postsService) {
        this.postsService = postsService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postsService.getPosts();
    }

    @GetMapping(path = "{id}")
    public Post getPostById(@PathVariable Long id) {
        return postsService.getPostById(id);
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postsService.addPost(post);
    }

    @DeleteMapping(path = "{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postsService.deletePost(id);
    }

    @PutMapping(path = "{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postsService.updatePost(id, post);
    }
}
