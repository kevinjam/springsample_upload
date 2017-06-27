package com.sampleimage.upload;

/**
 * Created by kevinjanvier on 15/06/2017.
 */
public class ErrorMessage {

    private String status;
    private String error;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public ErrorMessage(String status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
