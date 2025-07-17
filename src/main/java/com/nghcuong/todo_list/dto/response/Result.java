package com.nghcuong.todo_list.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {
    private String message;
    private int responseCode;
    private boolean success;

    public static Result success(String message) {
        return new Result(message, 200, true);
    }

    public static Result success() {
        return new Result("Thành công", 200, true);
    }

    public static Result error(String message, int responseCode) {
        return new Result(message, responseCode, false);
    }

    public static Result error(String message) {
        return new Result(message, 500, false);
    }

}
