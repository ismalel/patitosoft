package com.patitosoft.empadmin.model;

public class ReportGender {

    private double male;
    private double female;

    public ReportGender(double male, double female) {
        this.male = male;
        this.female = female;
    }

    public double getMale() {
        return male;
    }

    public void setMale(double male) {
        this.male = male;
    }

    public double getFemale() {
        return female;
    }

    public void setFemale(double female) {
        this.female = female;
    }
}
