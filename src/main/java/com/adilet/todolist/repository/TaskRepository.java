package com.adilet.todolist.repository;

import com.adilet.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value="SELECT DISTINCT t.* FROM Tasks t " +
            "LEFT JOIN TASKS_TAGS tt ON t.id = tt.task_id " +
            "LEFT JOIN TAGS tg ON tg.id = tt.tag_id " +
            "WHERE (tg.name IN :tagNames OR '' IN :tagNames) AND " +
            "(t.task_list_id = :listId OR :listId IS NULL)", nativeQuery = true)
    List<Task> findTasksByTagsAndListId(Integer listId, List<String> tagNames);
}
