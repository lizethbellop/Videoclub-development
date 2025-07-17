package org.thevault.videoclubAPI.model.dto.response;

import org.thevault.videoclubAPI.model.dto.UserSessionDTO;

public class LoginResponse {
    private UserSessionDTO user;
    private String message;
    private boolean success;

    public LoginResponse(UserSessionDTO user, String message, boolean success) {
        this.user = user;
        this.message = message;
        this.success = success;
    }

    public static LoginResponse success(UserSessionDTO user, String message){
        return new LoginResponse(user, message, true);
    }

    public static LoginResponse error(String message){
        return new LoginResponse(null, message, false);
    }

    public UserSessionDTO getUser() {
        return user;
    }

    public void setUser(UserSessionDTO user) {
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
}
