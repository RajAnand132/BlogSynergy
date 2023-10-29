package com.projectRaj.BloggingPlatform.controller;

import com.projectRaj.BloggingPlatform.models.Post;
import com.projectRaj.BloggingPlatform.models.PostType;
import com.projectRaj.BloggingPlatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public String createPost(@RequestParam String userName, @RequestParam String title,
                             @RequestParam PostType postType, @RequestParam String content) {
        return postService.createPost(userName,title,postType,content);
    }

    @GetMapping("/posts")
    public Object getPost(@RequestParam String userName, @RequestParam Integer commentPageNumber) {
        return postService.getPost(userName,commentPageNumber);
    }

    @PutMapping("/post")
    public String updatePost(@RequestParam String userName, @RequestParam Long postId,
                             @RequestParam String title, @RequestParam PostType postType,
                             @RequestParam String content) {
        return postService.updatePost(userName,postId, title,postType,content);
    }

    @DeleteMapping("/postId")
    public String deletePost(@RequestParam String userName, @RequestParam Long postId) {
        return postService.deletePost(userName,postId);
    }

    @GetMapping("/posts/search")
    public Object searchPosts(@RequestParam String userName, @RequestParam String query, @RequestParam Integer commentPageNumber) {
        // Implement the search logic here
        return postService.searchPosts(userName, query,commentPageNumber);
    }

}
