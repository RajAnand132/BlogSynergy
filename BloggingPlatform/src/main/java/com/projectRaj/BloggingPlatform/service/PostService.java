package com.projectRaj.BloggingPlatform.service;

import com.projectRaj.BloggingPlatform.models.Comment;
import com.projectRaj.BloggingPlatform.models.Post;
import com.projectRaj.BloggingPlatform.models.PostType;
import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.repo.CommentRepository;
import com.projectRaj.BloggingPlatform.repo.PostRepository;
import com.projectRaj.BloggingPlatform.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginCheckService loginCheckService;

    @Autowired
    CommentRepository commentRepository;

    public String createPost(String userName, String title, PostType postType,String content) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Post Creation Failed: Invalid user - The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            User author = userRepository.findByUserName(userName);
            Post newPost = new Post(null,title,postType,content,LocalDateTime.now(),LocalDateTime.now(),author,null);
            // Save the post using the repository
            postRepository.save(newPost);

            return "Post Created Successfully: The post is successfully uploaded into the repository.";
        } else {
            return "Access Denied: Post Creation Failed - You are not logged in. Please log in first.";
        }
    }


    public Object getPost(String userName, int page) {
        int pageSize = 10;  // Define the number of comments to fetch per page

        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Invalid user: The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            List<Post> posts = postRepository.findByAuthor(user);

            // Set the author property to null for each post
            for (Post post : posts) {
                post.setAuthor(null);

                // Set email, password, and posts to null for each commenter in the comments
                if (post.getComments() != null) {
                    int commentCount = post.getComments().size();
                    int startIndex = (page - 1) * pageSize;
                    int endIndex = Math.min(startIndex + pageSize, commentCount);

                    if (startIndex < commentCount) {
                        List<Comment> commentsToReturn = post.getComments().subList(startIndex, endIndex);
                        post.setComments(commentsToReturn);

                        for (Comment comment : commentsToReturn) {
                            if (comment.getCommenter() != null) {
                                comment.getCommenter().setEmail(null);
                                comment.getCommenter().setPassword(null);
                                comment.getCommenter().setPosts(null);
                            }
                        }
                    } else {
                        post.setComments(Collections.emptyList());
                    }
                }
            }

            if (posts.isEmpty()) {
                return "No posts found for the user: " + userName;
            }
            return posts;
        } else {
            return "Access Denied: You are not logged in. Please log in first.";
        }
    }


    public String updatePost(String userName, Long postId, String title, PostType postType, String content ) {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            return "Invalid user !!!";
        }

        if (loginCheckService.loggedIn(userName)) {
            // Find the post by ID
            Optional<Post> existingPostOptional = postRepository.findById(postId);

            if (existingPostOptional.isPresent()) {
                Post existingPost = existingPostOptional.get();

                // Ensure that the user is the owner of the post
                if (!existingPost.getAuthor().getUserId().equals(user.getUserId())) {
                    return "Post with ID " + postId + " does not belong to the user: " + userName;
                }

                // Update the fields
                existingPost.setTitle(title);
                existingPost.setPostType(postType);
                existingPost.setContent(content);
                existingPost.setCreationDateTime(existingPost.getCreationDateTime());
                existingPost.setLastModifiedDateTime(LocalDateTime.now());

                // Save the updated post
                postRepository.save(existingPost);
                return "Post with ID " + postId + " is updated";
            } else {
                return "Post with ID " + postId + " not found";
            }
        }

        return "Access Denied: You are not logged in. Please log in first.";
    }

    @Transactional
    public String deletePost(String userName, Long postId) {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            return "Invalid user !!!";
        }

        if (loginCheckService.loggedIn(userName)) {
            // Find the post by ID
            Optional<Post> existingPost = postRepository.findById(postId);

            if (existingPost.isPresent()) {
                Post post = existingPost.get();

                // Ensure that the user is the owner of the post
                if (post.getAuthor().getUserId().equals(user.getUserId())) {
                    // Delete all comments associated with the post
                    commentRepository.deleteByPost(post);

                    // Delete the post
                    postRepository.delete(post);
                    return "Post with ID: " + postId + " and its associated comments are deleted";
                } else {
                    return "Post with ID " + postId + " does not belong to the user: " + userName;
                }
            }

            return "Post with ID " + postId + " not found";
        }

        return "Access Denied: You are not logged in. Please log in first.";
    }

    public Object searchPosts(String userName, String query, int page) {
        int pageSize = 10;  // Define the number of comments to fetch per page

        // Find the user by username
        User user = userRepository.findByUserName(userName);

        // Check if the user exists
        if (user == null) {
            return "Invalid user: The specified user does not exist.";
        }

        // Check if the user is logged in
        if (loginCheckService.loggedIn(userName)) {
            // Implement the search logic here
            List<Post> searchResults = postRepository.findByTitleContainingOrContentContaining(query, query);

            if (searchResults.isEmpty()) {
                return "No posts found for the given query: " + query;
            }

            for (Post post : searchResults) {
                if (post.getAuthor() == null) {
                    post.setAuthor(null);
                } else {
                    // Nullify specific properties of the author
                    post.getAuthor().setEmail(null);
                    post.getAuthor().setPassword(null);
                    post.getAuthor().setPosts(null);
                }

                if (post.getComments() != null) {
                    int commentCount = post.getComments().size();
                    int startIndex = (page - 1) * pageSize;
                    int endIndex = Math.min(startIndex + pageSize, commentCount);

                    if (startIndex < commentCount) {
                        List<Comment> commentsToReturn = post.getComments().subList(startIndex, endIndex);
                        post.setComments(commentsToReturn);

                        for (Comment comment : commentsToReturn) {
                            if (comment.getCommenter() == null) {
                                comment.setCommenter(null);
                            } else {
                                // Nullify specific properties of the commenter
                                comment.getCommenter().setEmail(null);
                                comment.getCommenter().setPassword(null);
                                comment.getCommenter().setPosts(null);
                            }
                        }
                    } else {
                        post.setComments(Collections.emptyList());
                    }
                }
            }
            return searchResults;
        } else {
            return "Access Denied: You are not logged in. Please log in first.";
        }
    }

}
