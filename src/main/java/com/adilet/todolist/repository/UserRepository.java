package com.adilet.todolist.repository;

import com.adilet.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.isNonDisabled = false WHERE u.id = :id")
    void disableById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE User u SET u.isNonDisabled = true WHERE u.id = :id")
    void enableById(@Param("id") Integer id);
}
