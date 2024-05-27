package com.xuanduy.terrascope.dto.city.respond;

import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;

import java.util.List;

public class SimilarPeriodCityRecordDTO {
    private int id;
    private int number;
    private String name;
    private String countryName;
    private int startYear;
    private int endYear;
    private int yearLength;
    private Double averageValueOfTemperature;
    private int availableYearCount;
    private double temperatureDifference;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

}
