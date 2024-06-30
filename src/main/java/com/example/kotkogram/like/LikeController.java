package com.example.kotkogram.like;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Void> createLike(@RequestBody CreateLikeDto createLikeDto) {
        likeService.createLike(createLikeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteLike(@RequestParam Long likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok().build();
    }

}
