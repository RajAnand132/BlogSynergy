package com.projectRaj.BloggingPlatform.repo;

import com.projectRaj.BloggingPlatform.models.utilities.LoginCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginCheck,Long> {
}
