package org.thevault.videoclubAPI.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "Username or Email required")
    @Size(min = 3, max = 100, message = "Username or Email must be between 3 and 100 characteres")
    private String usernameOrEmail;

    @NotBlank(message = "Password required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public @NotBlank(message = "Username or Email required") String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(@NotBlank(message = "Username or Email required") String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public @NotBlank(message = "Password required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password required") String password) {
        this.password = password;
    }
}
