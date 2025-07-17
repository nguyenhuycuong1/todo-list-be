package com.nghcuong.todo_list.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private Result result;
    private T data;

    public ApiResponse(Result result, T data) {
        this.result = result;
        this.data = data;
    }

    public ApiResponse() {};

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse<T> success() {
        this.result = Result.builder()
                .message("Thành công")
                .responseCode(200)
                .success(true)
                .build();
        return this;
    }

}
