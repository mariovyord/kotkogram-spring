package com.example.kotkogram.posts;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository) {
        return args -> {
            Post post = new Post("Hello", "Hello, world!", LocalDate.now(), LocalDate.now());
            postRepository.save(post);
        };
    }
}
