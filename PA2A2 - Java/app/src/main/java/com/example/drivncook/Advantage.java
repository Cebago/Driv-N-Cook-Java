package com.example.drivncook;

public class Advantage {

    private int idAdvantage;
    private String advantageName;
    private String categoryName;
    private int nbPoint;

    public Advantage(int idAdvantage, String advantageName, String categoryName, int nbPoint) {
        this.idAdvantage = idAdvantage;
        this.advantageName = advantageName;
        this.categoryName = categoryName;
        this.nbPoint = nbPoint;
    }

    public int getIdAdvantage() {
        return idAdvantage;
    }

    public void setIdAdvantage(int idAdvantage) {
        this.idAdvantage = idAdvantage;
    }

    public String getAdvantageName() {
        return advantageName;
    }

    public void setAdvantageName(String advantageName) {
        this.advantageName = advantageName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint = nbPoint;
    }
}
