package com.nghcuong.todo_list.entity;

import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends BaseEntity {
    String title;
    String description;

    LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    TaskStatus status;
    @Enumerated(EnumType.STRING)
    Priority priority;

    @Column(name = "category_id")
    Long categoryId;
}
