package com.opahit.mschallengebackend.application.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public class ApiError {

    private final LocalDateTime timestamp;
    private final String code;
    private final String message;
    private List<String> detailMessages;
    private final String path;

    public ApiError(LocalDateTime timestamp, String code, String message, List<String> detailMessages, String path) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.detailMessages = detailMessages;
        this.path = path;
    }

    public ApiError(LocalDateTime timestamp, String code, String message, String path) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetailMessages() {
        return detailMessages;
    }

    public String getPath() {
        return path;
    }
}
