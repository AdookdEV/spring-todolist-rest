package com.adilet.todolist.repository;

import com.adilet.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value="SELECT tk.* FROM TASKS tk " +
            "INNER JOIN TASKS_TAGS tt ON tk.id = tt.task_id " +
            "INNER JOIN TAGS tg ON tg.id = tt.tag_id " +
            "WHERE tg.name = :tagName or tk.task_list_id = :listId", nativeQuery = true)
    List<Task> findTasksByTagAndListId(Integer listId, String tagName);
}
