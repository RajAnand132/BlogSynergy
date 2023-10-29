package com.projectRaj.BloggingPlatform.repo;

import com.projectRaj.BloggingPlatform.models.User;
import com.projectRaj.BloggingPlatform.models.utilities.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConnectionRepository extends JpaRepository<UserConnection,Long> {
    UserConnection findByFollowerAndFollowing(User follower, User following);

    List<UserConnection> findByFollowing(User user);
}
