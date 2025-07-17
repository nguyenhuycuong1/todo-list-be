package com.nghcuong.todo_list.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDTO {
    Long id;
    String title;
    String description;
    TaskStatus status;
    Priority priority;
    @JsonProperty("due_date")
    LocalDate dueDate;
    @JsonProperty("category_id")
    Long categoryId;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
}
