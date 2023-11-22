package com.adilet.todolist.dto;

import com.adilet.todolist.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public enum TaskDto {;
    private interface Id {Integer getId();}
    private interface Header { String getHeader();}
    private interface Description { String getDescription();}
    private interface IsDone { Boolean getIsDone();}
    private interface Deadline { LocalDate getDeadline();}
    private interface CreatedAt {LocalDate getCreatedAt();}
    private interface CreatorId {Integer getCreatorId();}
    private interface TagIds {Collection<Integer> getTagIds();}
    private interface TaskListId {Integer getTaskListId();}

    public enum Request {;
        @Value
        public static class Create implements Header, Description, CreatedAt, IsDone, Deadline, TagIds, CreatorId {
            String header;
            String description;
            LocalDate deadline;
            @JsonIgnore
            LocalDate createdAt = LocalDate.now();
            Boolean isDone = false;
            Integer taskListId;
            Integer creatorId;
            Collection<Integer> tagIds;

            public static Task toTask(TaskDto.Request.Create dto) {
                Task t = new Task();
                t.setTags(new ArrayList<>());
                BeanUtils.copyProperties(dto, t);
                return t;
            }
        }
    }
}
