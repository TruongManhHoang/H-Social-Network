package com.example.social_network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1009, "Role not found", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1010, "Role existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1011, "User not found", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(1012, "Post not found", HttpStatus.BAD_REQUEST),
    DELETE_POST(1013, "You can't delete another users post", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1014, "Invalid token!", HttpStatus.BAD_REQUEST),
    PROVIDE_TOKEN(1015, "Please provide a valid token!", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1016, "This email already used with another account", HttpStatus.BAD_REQUEST),
    USERNAME_AUTH(1017, "Invalid User", HttpStatus.BAD_REQUEST),
    PASSWORD_AUTH(1018, "Password not matched", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND(1019, "Comment not found", HttpStatus.BAD_REQUEST),
    CHAT_NOT_FOUND(1020, "Chat not found", HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
