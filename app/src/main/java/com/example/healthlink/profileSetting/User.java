package com.example.healthlink.profileSetting;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String country;
    public String medicalHistory;
    public String insuranceInfo;
    public String bloodType;


    public User(String firstName, String lastName, String email, String phoneNumber, String country, String medicalHistory, String insuranceInfo, String bloodType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.medicalHistory = medicalHistory;
        this.insuranceInfo = insuranceInfo;
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", insuranceInfo='" + insuranceInfo + '\'' +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
