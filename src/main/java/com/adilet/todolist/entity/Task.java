package com.adilet.todolist.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String header;
    private String description;
    private Boolean isDone = false;
    private LocalDate deadline;
    private LocalDate createdAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name="task_list_id", nullable = false)
    private TaskList taskList;

    @ManyToMany
    @JoinTable(
            name="tasks_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private  User creator;
}
