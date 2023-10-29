package com.projectRaj.BloggingPlatform.repo;

import com.projectRaj.BloggingPlatform.models.Post;
import com.projectRaj.BloggingPlatform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User user);

    List<Post> findByTitleContainingOrContentContaining(String query, String query1);
}
