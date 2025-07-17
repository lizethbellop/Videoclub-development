package org.thevault.videoclubAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate birthDate;
    private int strike;

    @Column(name = "isAccountActive", columnDefinition = "BIT")
    private boolean accountActive;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;


    public Client() {
    }

    public Client(int idClient, String firstName, String lastName, String phone, LocalDate birthDate, int strike, boolean accountActive, User user) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.strike = strike;
        this.accountActive = accountActive;
        this.user = user;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void setAccountActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
