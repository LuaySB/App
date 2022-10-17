package com.example.app;

public class User {
    public String name, email, homeAdress, mobileNumber, dateOfBirth, icaFav, coopvFav, coopkFav, lidlFav;

    public User() {}

    public User(String name, String email, String homeAdress, String mobileNumber, String dateOfBirth,
                String icaFav, String coopkFav, String coopvFav, String lidlFav) {
        this.name = name;
        this.email = email;
        this.homeAdress = homeAdress;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.icaFav = icaFav;
        this.coopkFav = coopkFav;
        this.coopvFav = coopvFav;
        this.lidlFav = lidlFav;

    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email; }

    public String getHomeAdress() {
        return homeAdress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getIcaFav() {
        return icaFav;
    }

    public String getCoopvFav() {
        return coopvFav;
    }

    public String getCoopkFav() { return coopkFav; }

    public String getLidlFav() {
        return lidlFav;
    }

}
