package com.nghcuong.todo_list.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TaskStatus {

    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    LATE("LATE");

    String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
