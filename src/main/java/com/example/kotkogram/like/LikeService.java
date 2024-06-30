package com.example.kotkogram.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kotkogram.post.Post;
import com.example.kotkogram.post.PostRepository;
import com.example.kotkogram.user.User;
import com.example.kotkogram.user.UserRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createLike(CreateLikeDto createLikeDto) {
        Post post = postRepository.findById(createLikeDto.postId).orElseThrow();
        User user = userRepository.findById(createLikeDto.userId).orElseThrow();

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);

        likeRepository.save(like);
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
