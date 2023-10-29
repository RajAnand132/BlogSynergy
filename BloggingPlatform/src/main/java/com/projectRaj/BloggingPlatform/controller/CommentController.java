package com.projectRaj.BloggingPlatform.controller;

import com.projectRaj.BloggingPlatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public String createComment(@RequestParam String userName, @RequestParam String comment, @RequestParam Long postId) {
        return commentService.createComment(userName,comment, postId);
    }

    @GetMapping("/commentId")
    public String getComment(@RequestParam String userName,@RequestParam Long commentId) {
        return commentService.getComment(userName,commentId);
    }

    @PutMapping("/commentId")
    public String updateComment(@RequestParam String userName, @RequestParam Long commentId, @RequestParam String comment) {
        return commentService.updateComment(userName,commentId, comment);
    }

    @DeleteMapping("/commentId")
    public String deleteComment(@RequestParam String userName, @RequestParam Long commentId) {
        return commentService.deleteComment(userName, commentId);
    }
}

