package org.thevault.videoclubAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String username;
    private String password;
    private String profilePicture;
    private String email;

    public User() {
    }

    public User(int idUser, String username, String password, String profilePicture, String email) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
