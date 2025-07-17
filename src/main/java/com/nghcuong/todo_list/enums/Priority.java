package com.nghcuong.todo_list.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Priority {

    LOW("Thấp"),
    MEDIUM("Trung bình"),
    HIGH("Cao");

    String status;

    Priority(String status) {
        this.status = status;
    }

}
