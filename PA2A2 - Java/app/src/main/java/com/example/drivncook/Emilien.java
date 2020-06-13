package com.example.drivncook;

public class Emilien {
    private int corps;
    private int spirit;
    private String nom;

    /**
     *
     * @param corps
     * @param spirit
     * @param nom
     */
    public Emilien(int corps, int spirit, String nom) {
        this.corps = corps;
        this.spirit = spirit;
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public int getCorps() {
        return corps;
    }

    /**
     *
     * @param corps
     */
    public void setCorps(int corps) {
        this.corps = corps;
    }

    /**
     *
     * @return
     */
    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
