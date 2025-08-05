package com.UserAuthService.UserAuthService.repo;

import com.UserAuthService.UserAuthService.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {
    Optional<Session> findByTokenAndUser_Id(String s, Long userId);
}
