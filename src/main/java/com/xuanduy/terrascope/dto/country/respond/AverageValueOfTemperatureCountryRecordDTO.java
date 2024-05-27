package com.xuanduy.terrascope.dto.country.respond;

import static java.lang.Math.round;

public class AverageValueOfTemperatureCountryRecordDTO {
    private long id;
    private int number;
    private String name;
    private int startYear;
    private int endYear;
    private Double averageValueOfTemperature;
    private int availableYearCount;

    public Double getAverageValueOfTemperature() {
        return averageValueOfTemperature;
    }

    public void setAverageValueOfTemperature(Double averageValueOfTemperature) {
        averageValueOfTemperature = Math.round(averageValueOfTemperature * 100000.0) / 100000.0;
        this.averageValueOfTemperature = averageValueOfTemperature;
    }

    public int getAvailableYearCount() {
        return availableYearCount;
    }

    public void setAvailableYearCount(int availableYearCount) {
        this.availableYearCount = availableYearCount;
    }



    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
