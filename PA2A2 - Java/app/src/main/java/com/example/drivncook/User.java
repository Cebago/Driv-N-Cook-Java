package com.example.drivncook;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String lastname;
    private String firstname;
    private String email;
    private String pwd;
    private String token;
    private Fidelity fidelity;

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Fidelity getFidelity() {
        return fidelity;
    }

    public void setFidelity(Fidelity fidelity) {
        this.fidelity = fidelity;
    }
}
