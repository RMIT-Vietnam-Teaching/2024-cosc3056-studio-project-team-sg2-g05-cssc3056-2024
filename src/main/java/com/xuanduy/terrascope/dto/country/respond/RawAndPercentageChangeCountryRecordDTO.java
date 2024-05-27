package com.xuanduy.terrascope.dto.country.respond;

import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;

import java.math.BigDecimal;

public class RawAndPercentageChangeCountryRecordDTO {
    private int id;
    private Integer number;
    private String name;
    private String code;
    private Integer startYear;
    private Integer endYear;
    private Double startYearTemperature;
    private Double endYearTemperature;
    private Integer startYearPopulation;
    private Integer endYearPopulation;
    private Double rawValueChangeTemperature;
    private Double percentageChangeTemperature;
    private Integer rawValueChangePopulation;
    private Double percentageChangePopulation;

    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getEndYearPopulation() {
        return endYearPopulation;
    }

    public void setEndYearPopulation(Integer endYearPopulation) {
        this.endYearPopulation = endYearPopulation;
    }

    public Double getEndYearTemperature() {
        return endYearTemperature;
    }

    public void setEndYearTemperature(Double endYearTemperature) {
        this.endYearTemperature = endYearTemperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentageChangePopulation() {
        return percentageChangePopulation;
    }

    public void setPercentageChangePopulation(Double percentageChangePopulation) {
        percentageChangePopulation = Math.round(percentageChangePopulation * 100000.0) / 1000.0;
        this.percentageChangePopulation = percentageChangePopulation;
    }

    public Double getPercentageChangeTemperature() {
        return percentageChangeTemperature;
    }

    public void setPercentageChangeTemperature(Double percentageChangeTemperature) {
        percentageChangeTemperature = Math.round(percentageChangeTemperature * 100000.0) / 1000.0;
        this.percentageChangeTemperature = percentageChangeTemperature;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer rank) {
        this.number = rank;
    }

    public Integer getRawValueChangePopulation() {
        return rawValueChangePopulation;
    }

    public void setRawValueChangePopulation(Integer rawValueChangePopulation) {

        this.rawValueChangePopulation = rawValueChangePopulation;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Double getRawValueChangeTemperature() {
        return rawValueChangeTemperature;
    }

    public void setRawValueChangeTemperature(Double rawValueChangeTemperature) {
        rawValueChangeTemperature = Math.round(rawValueChangeTemperature * 100000.0) / 100000.0;
        this.rawValueChangeTemperature = rawValueChangeTemperature;
    }

    public Integer getStartYearPopulation() {
        return startYearPopulation;
    }

    public void setStartYearPopulation(Integer startYearPopulation) {
        this.startYearPopulation = startYearPopulation;
    }

    public Double getStartYearTemperature() {
        return startYearTemperature;
    }

    public void setStartYearTemperature(Double startYearTemperature) {
        this.startYearTemperature = startYearTemperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
