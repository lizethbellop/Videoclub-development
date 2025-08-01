package org.thevault.videoclub_desktop.model;

import org.thevault.videoclub_desktop.model.enumerations.UserType;

public class UserDTO {
    private int userId;
    private String username;
    private String name;
    private String profilePicture;
    private UserType userType;
    private String role;
    private String email;

    public UserDTO() {
    }

    public UserDTO(int userId, String username, String name, String profilePicture, UserType userType, String role, String email) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.profilePicture = profilePicture;
        this.userType = userType;
        this.role = role;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", userType=" + userType +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
