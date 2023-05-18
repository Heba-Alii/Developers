package com.example.developers.model.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;

@Entity(tableName = "dev_table")
public class DeveloperEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String title;
    private double salary;
    private double ponus;
    private int absence;
    private int vacation;
    private int medicalInsurance;
    private int socialInsurance;

    public DeveloperEntity(String uid, String name, String title, double salary, double ponus, int absence, int vacation, int medicalInsurance, int socialInsurance) {
        this.uid = uid;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.ponus = ponus;
        this.absence = absence;
        this.vacation = vacation;
        this.medicalInsurance = medicalInsurance;
        this.socialInsurance = socialInsurance;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPonus() {
        return ponus;
    }

    public void setPonus(double ponus) {
        this.ponus = ponus;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    public int getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(int medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public int getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(int socialInsurance) {
        this.socialInsurance = socialInsurance;
    }
}
