package com.nghcuong.todo_list.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 BAD REQUEST
    VALIDATION_ERROR(400, "Dữ liệu không hợp lệ", HttpStatus.BAD_REQUEST),
    MISSING_REQUEST_PARAM(4001, "Thiếu tham số bắt buộc", HttpStatus.BAD_REQUEST),

    // 401 UNAUTHORIZED
    UNAUTHORIZED(401, "Chưa xác thực", HttpStatus.UNAUTHORIZED),

    // 403 FORBIDDEN
    FORBIDDEN(403, "Không có quyền truy cập", HttpStatus.FORBIDDEN),

    // 404 NOT FOUND
    RESOURCE_NOT_FOUND(404, "Không tìm thấy tài nguyên", HttpStatus.NOT_FOUND),

    // 409 CONFLICT
    DUPLICATE_RESOURCE(409, "Tài nguyên đã tồn tại", HttpStatus.CONFLICT),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}