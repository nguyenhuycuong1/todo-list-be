package com.nghcuong.todo_list.exception;

public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
