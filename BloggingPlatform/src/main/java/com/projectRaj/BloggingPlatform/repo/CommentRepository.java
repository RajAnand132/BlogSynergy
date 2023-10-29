package com.projectRaj.BloggingPlatform.repo;

import com.projectRaj.BloggingPlatform.models.Comment;
import com.projectRaj.BloggingPlatform.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByPost(Post post);
}
