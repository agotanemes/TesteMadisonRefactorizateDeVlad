package com.firestarters.models;

public class BillingInf {
    String firstN;
    String middleN;
    String lastN;
    String company;
    String emailAdr;
    String address;
    String streetAdr2;
    String city;
    String state;
    String zip;
    String country;
    String telephone;
    String fax;

    @Override
    public String toString() {
        return "BillingInf{" +
                "firstN='" + firstN + '\'' +
                ", middleN='" + middleN + '\'' +
                ", lastN='" + lastN + '\'' +
                ", company='" + company + '\'' +
                ", emailAdr='" + emailAdr + '\'' +
                ", address='" + address + '\'' +
                ", streetAdr2='" + streetAdr2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getMiddleN() {
        return middleN;
    }

    public void setMiddleN(String middleN) {
        this.middleN = middleN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmailAdr() {
        return emailAdr;
    }

    public void setEmailAdr(String emailAdr) {
        this.emailAdr = emailAdr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetAdr2() {
        return streetAdr2;
    }

    public void setStreetAdr2(String streetAdr2) {
        this.streetAdr2 = streetAdr2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
