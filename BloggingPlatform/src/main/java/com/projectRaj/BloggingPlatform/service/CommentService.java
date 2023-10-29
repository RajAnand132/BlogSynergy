package com.projectRaj.BloggingPlatform.service;

import com.projectRaj.BloggingPlatform.models.Comment;
import com.projectRaj.BloggingPlatform.models.Post;
import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.repo.CommentRepository;
import com.projectRaj.BloggingPlatform.repo.PostRepository;
import com.projectRaj.BloggingPlatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginCheckService loginCheckService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;


    public String createComment(String userName, String comment, Long postId) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Comment Creation Failed: Invalid user - The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            User author = userRepository.findByUserName(userName);
            Optional<Post> postOptional = postRepository.findById(postId);

            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                Comment newComment = new Comment(null, comment, LocalDateTime.now(),LocalDateTime.now(), post, author);

                // Save the comment using the repository
                commentRepository.save(newComment);

                return "Comment Created Successfully: The comment is added to the postId : "+postId +".";
            } else {
                return "Comment Creation Failed: Post not found - The specified post does not exist.";
            }
        } else {
            return "Access Denied: Comment Creation Failed - You are not logged in. Please log in first.";
        }
    }

    public String getComment(String userName, Long commentId) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Comment Retrieval Failed: Invalid user - The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);

            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();

                // Return the comment as a response
                return "Comment ID: " + comment.getCommentId() + "\n"
                        + "Text: " + comment.getComment() + "\n"
                        + "Author: " + comment.getCommenter().getUserName() + "\n"
                        + "Post ID: "+ comment.getPost().getPostId() + "\n"
                        + "Post: " + comment.getPost().getTitle() + "\n"
                        + "Created At: " + comment.getCommentCreationTimeStamp() + "\n"
                        + "Modified At: " + comment.getCommentModifiedTimeStamp();
            } else {
                return "Comment Retrieval Failed: Comment not found - The specified comment does not exist.";
            }
        } else {
            return "Access Denied: Comment Retrieval Failed - You are not logged in. Please log in first.";
        }
    }

    public String updateComment(String userName, Long commentId, String commentText) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Comment Update Failed: Invalid user - The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);

            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();

                // Check if the user is the author of the comment
                if (comment.getCommenter().getUserName().equals(userName)) {
                    // Update the comment text
                    comment.setComment(commentText);
                    comment.setCommentModifiedTimeStamp(LocalDateTime.now());

                    // Save the updated comment
                    commentRepository.save(comment);

                    return "Comment Updated Successfully: The comment has been updated.";
                } else {
                    return "Comment Update Failed: You are not the author of this comment.";
                }
            } else {
                return "Comment Update Failed: Comment not found - The specified comment does not exist.";
            }
        } else {
            return "Access Denied: Comment Update Failed - You are not logged in. Please log in first.";
        }
    }

    public String deleteComment(String userName, Long commentId) {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            return "Comment Deletion Failed: Invalid user - The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            Optional<Comment> commentOptional = commentRepository.findById(commentId);

            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                User commenter = comment.getCommenter();

                // Check if the user is the author of the comment or the owner of the post
                if (commenter.getUserName().equals(userName) || comment.getPost().getAuthor().getUserName().equals(userName)) {
                    // Delete the comment
                    commentRepository.delete(comment);

                    return "Comment Deleted Successfully: The comment has been removed.";
                } else {
                    return "Comment Deletion Failed: You are not the author of this comment or the owner of the post.";
                }
            } else {
                return "Comment Deletion Failed: Comment not found - The specified comment does not exist.";
            }
        } else {
            return "Access Denied: Comment Deletion Failed - You are not logged in. Please log in first.";
        }
    }
}
