package com.adilet.todolist.service;


import com.adilet.todolist.entity.Tag;
import com.adilet.todolist.entity.Task;
import com.adilet.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


    @Test
    public void shouldReturnTasksByTagsAndListId() {
        List<Task> tasksWithImportant = setUpTasks();
        Mockito.when(taskRepository.findTasksByTagsAndListId(1, List.of("important")))
                .thenReturn(tasksWithImportant);

        // Tags by which it needs to find tasks.
        String[] tags = new String[] {"important"};

        List<Task> filteredTasks = taskService.findByTagsAndListId(1, tags);

        Assertions.assertNotNull(filteredTasks);
        Assertions.assertEquals(3, filteredTasks.size());



    }

    private List<Tag> setUpTags() {
        Tag tag1 = Tag.builder().id(1).name("important").build();
        Tag tag2 = Tag.builder().id(2).name("coding").build();
        Tag tag3 = Tag.builder().id(3).name("sport").build();
        return List.of(tag1, tag2, tag3);
    }

    private List<Task> setUpTasks() {
        List<Tag> tags = setUpTags();
        Task task1 = Task.builder()
                .id(1)
                .header("Task 1")
                .tags(new ArrayList<>())
                .build();
        task1.getTags().add(tags.get(2));
        task1.getTags().add(tags.get(1));

        Task task2 = Task.builder()
                .id(2)
                .header("Task 2")
                .tags(new ArrayList<>())
                .build();
        task2.getTags().add(tags.get(1));
        task2.getTags().add(tags.get(0));

        Task task3 = Task.builder()
                .id(3)
                .header("Task 3")
                .tags(new ArrayList<>())
                .build();
        task3.getTags().add(tags.get(1));


        return List.of(task1, task2, task3);
    }
}
