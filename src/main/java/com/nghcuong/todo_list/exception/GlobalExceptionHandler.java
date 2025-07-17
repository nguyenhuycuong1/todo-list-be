package com.nghcuong.todo_list.exception;

import com.nghcuong.todo_list.dto.response.ApiResponse;
import com.nghcuong.todo_list.dto.response.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setResult(Result.builder()
                        .responseCode(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .success(false)
                .build());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(apiResponse);
    }

}
