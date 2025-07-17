package com.nghcuong.todo_list.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TaskStatus {

    PENDING("Mới tạo"),
    IN_PROGRESS("Đang làm"),
    DONE("Hoàn thành"),
    LATE("Muộn");

    String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
