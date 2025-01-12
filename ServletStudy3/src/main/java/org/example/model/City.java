package org.example.model;

public class City {
    protected int id;
    protected String cityName;
    protected int population;
    protected String country;

    public City() {
    }

    public City(int id, String cityName, int population, String country) {
        super();
        this.id = id;
        this.cityName = cityName;
        this.population = population;
        this.country = country;
    }

    public City(String cityName, int population, String country) {
        super();
        this.cityName = cityName;
        this.population = population;
        this.country = country;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
