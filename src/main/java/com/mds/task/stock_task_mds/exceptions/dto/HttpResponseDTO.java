package com.mds.task.stock_task_mds.exceptions.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class HttpResponseDTO {
    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public HttpResponseDTO() {
    }

    public HttpResponseDTO(final String message, final HttpStatus status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpResponseDTO that = (HttpResponseDTO) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(timestamp, that.timestamp) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timestamp, status);
    }

    @Override
    public String toString() {
        return "HttpResponseDTO{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
