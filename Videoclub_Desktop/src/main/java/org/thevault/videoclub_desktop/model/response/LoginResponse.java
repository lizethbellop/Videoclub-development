package org.thevault.videoclub_desktop.model.response;

import org.thevault.videoclub_desktop.model.UserDTO;

public class LoginResponse {
    private UserDTO user;
    private String message;
    private boolean success;

    public LoginResponse() {
    }

    public LoginResponse(UserDTO user, String message, boolean success) {
        this.user = user;
        this.message = message;
        this.success = success;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
