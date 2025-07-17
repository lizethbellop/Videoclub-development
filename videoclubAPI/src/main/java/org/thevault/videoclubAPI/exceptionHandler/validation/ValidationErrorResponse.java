package org.thevault.videoclubAPI.exceptionHandler.validation;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse {
    private int status;
    private String error;
    private LocalDateTime timestamp;
    private Map<String, String> fieldErrors;
    private String summary;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(int status, String error, LocalDateTime timestamp, Map<String, String> fieldErrors, String summary) {
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
        this.summary = summary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
