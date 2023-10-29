package com.projectRaj.BloggingPlatform.controller;

import com.projectRaj.BloggingPlatform.service.UserConnectionService;
import com.projectRaj.BloggingPlatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConnectionService userConnectionService;

    @PostMapping("/signUp")
    public String registerUser(@RequestParam @Valid String userName, @RequestParam @Valid String email, @RequestParam @Valid String password){
        return userService.registerUser(userName,email,password);
    }

    @PostMapping("/signIn")
    public String loginUser(@RequestParam String userName , @RequestParam String password) {
        // Handle user utilities and return the authenticated user
        return userService.loginUser(userName, password);
    }

    @PostMapping("/signOut")
    public String logOffUser(@RequestParam String userName) {
        // Handle user signout and make user log off
        return userService.logOutUser(userName);
    }

    @PostMapping("/follow")
    public String followUser(@RequestParam String userName, @RequestParam String userNameToFollow) {
        return userConnectionService.followUser(userName,userNameToFollow);
    }

    @PostMapping("/unfollow")
    public String unfollowUser(@RequestParam String userName, @RequestParam String userNameToUnFollow) {
        return userConnectionService.unfollowUser(userName, userNameToUnFollow);
    }

    @GetMapping("/followers")
    public Object getFollowers(@RequestParam String userName) {
        // Fetch the user's followers
        return userConnectionService.getFollowers(userName);
    }

}


