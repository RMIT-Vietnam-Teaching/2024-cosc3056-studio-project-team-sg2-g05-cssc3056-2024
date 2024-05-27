package com.xuanduy.terrascope.dto.state.respond;

import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;

import java.util.List;

public class TemperatureChangeStateRecordDTO{
    private int id;
    private int number;
    private String name;
    private String countryName;
    private Integer startYear;
    private Integer endYear;
    private Double startYearTemperature;
    private Double endYearTemperature;
    private Double rawValueChangeTemperature;
    private Double percentageChangeTemperature;
    private Double averagePercentageChangeTemperature;
    private int availableYearCount;
    public int getAvailableYearCount() {
        return availableYearCount;
    }

    public void setAvailableYearCount(int availableYearCount) {
        this.availableYearCount = availableYearCount;
    }




    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getAveragePercentageChangeTemperature() {
        return averagePercentageChangeTemperature;
    }

    public void setAveragePercentageChangeTemperature(Double averagePercentageChangeTemperature) {
        averagePercentageChangeTemperature = Math.round(averagePercentageChangeTemperature * 100000.0) / 1000.0;
        this.averagePercentageChangeTemperature = averagePercentageChangeTemperature;
    }


    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Double getEndYearTemperature() {
        return endYearTemperature;
    }

    public void setEndYearTemperature(Double endYearTemperature) {
        this.endYearTemperature = endYearTemperature;
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

    public Double getRawValueChangeTemperature() {
        return rawValueChangeTemperature;
    }

    public void setRawValueChangeTemperature(Double rawValueChangeTemperature) {
        rawValueChangeTemperature = Math.round(rawValueChangeTemperature * 100000.0) / 100000.0;
        this.rawValueChangeTemperature = rawValueChangeTemperature;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Double getStartYearTemperature() {
        return startYearTemperature;
    }

    public void setStartYearTemperature(Double startYearTemperature) {
        this.startYearTemperature = startYearTemperature;
    }

    public Double getPercentageChangeTemperature() {
        return percentageChangeTemperature;
    }

    public void setPercentageChangeTemperature(Double percentageChangeTemperature) {
        percentageChangeTemperature = Math.round(percentageChangeTemperature * 100000.0) / 1000.0;
        this.percentageChangeTemperature = percentageChangeTemperature;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    ;
}
