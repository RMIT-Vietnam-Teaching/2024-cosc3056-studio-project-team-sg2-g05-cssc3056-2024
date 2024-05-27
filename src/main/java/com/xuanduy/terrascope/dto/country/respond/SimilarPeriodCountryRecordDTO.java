package com.xuanduy.terrascope.dto.country.respond;

public class SimilarPeriodCountryRecordDTO {
    private int id;
    private int number;
    private String name;
    private int startYear;
    private int endYear;
    private int yearLength;
    private Double averageValueOfTemperature;
    private int availableYearCount;
    private double temperatureDifference;

    public int getAvailableYearCount() {
        return availableYearCount;
    }

    public void setAvailableYearCount(int availableYearCount) {
        this.availableYearCount = availableYearCount;
    }

    public Double getAverageValueOfTemperature() {
        return averageValueOfTemperature;
    }

    public void setAverageValueOfTemperature(Double averageValueOfTemperature) {
        this.averageValueOfTemperature = averageValueOfTemperature;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public double getTemperatureDifference() {
        return temperatureDifference;
    }

    public void setTemperatureDifference(double temperatureDifference) {
        temperatureDifference = Math.round(temperatureDifference * 100000.0) / 100000.0;
        this.temperatureDifference = temperatureDifference;
    }

    public int getYearLength() {
        return yearLength;
    }

    public void setYearLength(int yearLength) {
        this.yearLength = yearLength;
    }
}
