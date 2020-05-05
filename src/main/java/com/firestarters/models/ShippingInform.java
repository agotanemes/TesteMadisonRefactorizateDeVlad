package com.firestarters.models;

import java.util.Objects;

public class ShippingInform {
    String firstName;
    String lastName;
    String streetAddr;
    String city;
    String zip;
    String telephone;
    String state;
    String country;
    @Override
    public String toString() {
        return "ShippingInform{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetAddr='" + streetAddr + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", telephone='" + telephone + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInform that = (ShippingInform) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                streetAddr.equals(that.streetAddr) &&
                city.equals(that.city) &&
                zip.equals(that.zip) &&
                telephone.equals(that.telephone) &&
                state.equals(that.state) &&
                country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, streetAddr, city, zip, telephone, state, country);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddr() {
        return streetAddr;
    }

    public void setStreetAddr(String streetAddr) {
        this.streetAddr = streetAddr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
