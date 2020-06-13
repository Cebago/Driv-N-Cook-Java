package com.example.drivncook;

import java.io.Serializable;

public class Fidelity implements Serializable {
    private int idFidelity;
    private int points;

    public Fidelity(int idFidelity, int points) {
        this.idFidelity = idFidelity;
        this.points = points;
    }

    public int getIdFidelity() {
        return idFidelity;
    }

    public void setIdFidelity(int idFidelity) {
        this.idFidelity = idFidelity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
