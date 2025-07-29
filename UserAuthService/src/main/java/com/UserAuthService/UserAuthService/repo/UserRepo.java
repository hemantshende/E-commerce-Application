package com.UserAuthService.UserAuthService.repo;

import com.UserAuthService.UserAuthService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User save(User user);
}
