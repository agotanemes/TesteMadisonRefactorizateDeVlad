package com.firestarters.models;

public class Wheather {
    String City;
    String Temperature;
    String Humidity;
    String WeatherDescription;
    String WindSpeed;
    String WindDirectionDegree;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getWeatherDescription() {
        return WeatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        WeatherDescription = weatherDescription;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getWindDirectionDegree() {
        return WindDirectionDegree;
    }

    public void setWindDirectionDegree(String windDirectionDegree) {
        WindDirectionDegree = windDirectionDegree;
    }
}
