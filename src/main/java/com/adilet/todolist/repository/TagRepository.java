package com.adilet.todolist.repository;

import com.adilet.todolist.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findByCreatorId(Integer id);

    @Query("SELECT t FROM Tag t WHERE t.creator.username = :username")
    List<Tag> findByUsername(String username);

    Optional<Tag> findTagByIdAndCreator_Username(Integer id, String username);
}
