package com.taskmanager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {

    private final int status;
    private final String error;
    private final String message;
    private final LocalDateTime timestamp;
    private final String path;
}
