package com.projectRaj.BloggingPlatform.service;

import com.projectRaj.BloggingPlatform.models.utilities.LoginCheck;
import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.repo.LoginRepository;
import com.projectRaj.BloggingPlatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginCheckService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;

    public Boolean loggedIn(String userName){
        // Find the user by username
        User user = userRepository.findByUserName(userName);
        Optional<LoginCheck> loggedInUser = loginRepository.findById(user.getUserId());
        if(loggedInUser.isPresent() && loggedInUser.get().getLoggedIn()){
            return true;
        }
        return false;
    }
}
