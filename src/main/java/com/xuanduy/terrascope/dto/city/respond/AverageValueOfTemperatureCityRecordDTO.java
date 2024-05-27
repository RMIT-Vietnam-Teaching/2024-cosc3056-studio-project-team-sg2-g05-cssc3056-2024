package com.xuanduy.terrascope.dto.city.respond;

import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;

import java.util.List;

public class AverageValueOfTemperatureCityRecordDTO {
    private int id;
    private int number;
    private String name;
    private String countryName;
    private int startYear;
    private int endYear;
    private Double averageValueOfTemperature;
    private int availableYearCount;


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
        averageValueOfTemperature = Math.round(averageValueOfTemperature * 100000.0) / 100000.0;
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
