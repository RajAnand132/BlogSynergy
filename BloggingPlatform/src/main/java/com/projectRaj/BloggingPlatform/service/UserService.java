package com.projectRaj.BloggingPlatform.service;

import com.projectRaj.BloggingPlatform.models.utilities.LoginCheck;
import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.repo.LoginRepository;
import com.projectRaj.BloggingPlatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginCheckService loginCheckService;
    public String registerUser(String userName, String email, String password) {
        // Find the user by username
        User existingUser = userRepository.findByUserName(userName);

        if (existingUser != null) {
            // Handle the case where the username already exists
            return "Registration Failed: A user with the username '" + existingUser.getUserName() + "' already exists.";
        }

        // Check if the email is already in use
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail != null) {
            // Handle the case where the email is already in use
            return "Registration Failed: The email '" + email + "' is already in use.";
        }

        try {
            User newUser = new User();
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            newUser.setUserName(userName);
            newUser.setPassword(encryptedPassword);
            newUser.setEmail(email);

            // Save the new user
            userRepository.save(newUser);

            return "Registration Successful: User with username '" + newUser.getUserName() + "' is now registered.";
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where password encryption fails
            return "Registration Failed: Internal Server Error while registering, please try again later.";
        }
    }



    public String loginUser(String userName, String password) {
        // Find the user by username
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            // Handle the case where the user does not exist
            return "User not found";
        }

        Optional<LoginCheck> userOptional = loginRepository.findById(user.getUserId());

        if (userOptional.isPresent()) {
            LoginCheck loggedInUser = userOptional.get();

            // Check if the user is already logged in
            if (loggedInUser.getLoggedIn()) {
                // If the user is already logged in, return a message
                return "You are already logged in.";
            } else {
                // Log the user in
                loggedInUser.setLoggedIn(true);

                // Save the utilities record
                loginRepository.save(loggedInUser);
                return "Successfully logged in.";
            }
        } else {
            // Create a new utilities record for the user as they are not already logged in
            try {
                String encryptedPassword = PasswordEncryptor.encrypt(password);

                if (user.getPassword().equals(encryptedPassword)) {
                    LoginCheck loginCheck = new LoginCheck();
                    loginCheck.setLoggedIn(true);
                    loginCheck.setUser(user);

                    // Save the utilities record
                    loginRepository.save(loginCheck);

                    return "Successfully logged in.";
                } else {
                    // Handle the case where the password is incorrect
                    return "Invalid Credentials.";
                }
            } catch (NoSuchAlgorithmException e) {
                // Handle the case where utilities fails due to an internal server error
                return "Internal Server Error while logging in, please try again later.";
            }
        }
    }

    public String logOutUser(String userName) {
        // Find the user by username
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            // Handle the case where the user does not exist
            return "User not found";
        }

        try {
            Optional<LoginCheck> loggedInUserOptional = loginRepository.findById(user.getUserId());

            if (loggedInUserOptional.isPresent()) {
                LoginCheck loggedInUser = loggedInUserOptional.get();
                // Log the user off
                loggedInUser.setLoggedIn(false);
                loggedInUser.setUser(user);

                // Save the utilities record for false
                loginRepository.save(loggedInUser);

                return "Successfully logged off. You are now logged out.";
            } else {
                // Handle the case where the user is not logged in
                return "You are not currently logged in. No action taken.";
            }
        } catch (Exception e) {
            // Handle the case where an internal server error occurs
            return "Internal Server Error while logging out, please try again later.";
        }
    }
}
