package com.saferent.repository;

import com.saferent.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
}
