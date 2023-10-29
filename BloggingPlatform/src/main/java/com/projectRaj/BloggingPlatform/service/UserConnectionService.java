package com.projectRaj.BloggingPlatform.service;

import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.models.utilities.UserConnection;
import com.projectRaj.BloggingPlatform.repo.UserConnectionRepository;
import com.projectRaj.BloggingPlatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserConnectionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConnectionRepository userConnectionRepository;

    @Autowired
    private LoginCheckService loginCheckService;

    public String followUser(String userName, String userNameToFollow) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Invalid user: The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            User follower = userRepository.findByUserName(userName);
            User following = userRepository.findByUserName(userNameToFollow);

            if (following != null) {
                if (follower.getUserId().equals(following.getUserId())) {
                    return "Follow Failed: You cannot follow yourself.";
                }

                // Check if the user is already following the specified user
                UserConnection existingConnection = userConnectionRepository.findByFollowerAndFollowing(follower, following);

                if (existingConnection != null) {
                    return "Follow Failed: You are already following " + following.getUserName() + ".";
                }

                UserConnection userConnection = new UserConnection();
                userConnection.setFollower(follower);
                userConnection.setFollowing(following);
                userConnection.setCreatedAt(new Date());
                userConnectionRepository.save(userConnection);

                return "You have started following " + following.getUserName() + ".";
            } else {
                return "Follow Failed: The user you are trying to follow does not exist.";
            }
        }

        return "Access Denied: You are not logged in. Please log in first.";
    }


    public String unfollowUser(String userName, String userNameToUnFollow) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return "Invalid user: The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            User follower = userRepository.findByUserName(userName);
            User following = userRepository.findByUserName(userNameToUnFollow);

            if (following != null) {
                // Check if the user is trying to unfollow themselves
                if (follower.getUserId().equals(following.getUserId())) {
                    return "Unfollow Failed: You cannot unfollow yourself.";
                }

                UserConnection userConnection = userConnectionRepository.findByFollowerAndFollowing(follower, following);
                if (userConnection != null) {
                    userConnectionRepository.delete(userConnection);
                    return "You have unfollowed " + following.getUserName() + ".";
                } else {
                    return "Unfollow Failed: You are not currently following " + following.getUserName() + ".";
                }
            } else {
                return "Unfollow Failed: The user you are trying to unfollow does not exist.";
            }
        }
        return "Access Denied: You are not logged in. Please log in first.";
    }


    public Object getFollowers(String userName) {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            return "Invalid user: The specified user does not exist.";
        }

        if (loginCheckService.loggedIn(userName)) {
            Map<String,String> usersHandleAndPost =new HashMap<>();
            // Find followers for the user
            List<User> followers = new ArrayList<>();

            List<UserConnection> userConnections = userConnectionRepository.findByFollowing(user);
            for (UserConnection userConnection : userConnections) {
                followers.add(userConnection.getFollower());
            }

            // Adding all followers userHandles into list
            for (User follower : followers) {
                usersHandleAndPost.put(follower.getUserName(),"POSTS:- "+follower.getPosts().size());
            }
            if (!usersHandleAndPost.isEmpty()) {
                return usersHandleAndPost;
            } else {
                return "You don't have any followers yet.";
            }
        }
        return "Access Denied: You are not logged in. Please log in first.";
    }

}
