package com.askme.dreamhackathon.smile;

public class User {
    String name,email,password,contactNumber,type,pregnancyDate;

    public User(String name, String email, String password, String contactNumber, String type, String pregnancyDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.type = type;
        this.pregnancyDate = pregnancyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPregnancyDate() {
        return pregnancyDate;
    }

    public void setPregnancyDate(String pregnancyDate) {
        this.pregnancyDate = pregnancyDate;
    }
}
