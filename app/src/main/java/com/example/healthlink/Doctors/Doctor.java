package com.example.healthlink.Doctors;

import android.net.Uri;

public class Doctor {
    private String name, address, phone,imgSrc, specialization ;

    public Doctor(String name, String address, String phone, String imgSrc , String specialization  ) {
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.phone = phone;
        this.imgSrc = imgSrc;
    }

    public Doctor(String name, String address, String phone, String imgSrc) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.imgSrc = imgSrc;
        this.specialization = "General";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgSrc() {
        return imgSrc;
    }

}
