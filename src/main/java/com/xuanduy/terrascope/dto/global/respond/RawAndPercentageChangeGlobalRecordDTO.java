package com.xuanduy.terrascope.dto.global.respond;

public class RawAndPercentageChangeGlobalRecordDTO {
    private Integer startYear;
    private Integer endYear;
    private Double startYearTemperature;
    private Double endYearTemperature;
    private Long startYearPopulation;
    private Long endYearPopulation;
    private Double rawValueChangeTemperature;
    private Double percentageChangeTemperature;
    private Long rawValueChangePopulation;
    private Double percentageChangePopulation;

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Long getEndYearPopulation() {
        return endYearPopulation;
    }

    public void setEndYearPopulation(Long endYearPopulation) {
        this.endYearPopulation = endYearPopulation;
    }

    public Double getEndYearTemperature() {
        return endYearTemperature;
    }

    public void setEndYearTemperature(Double endYearTemperature) {
        this.endYearTemperature = endYearTemperature;
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

    public Long getRawValueChangePopulation() {
        return rawValueChangePopulation;
    }

    public void setRawValueChangePopulation(Long rawValueChangePopulation) {
        this.rawValueChangePopulation = rawValueChangePopulation;
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

    public Long getStartYearPopulation() {
        return startYearPopulation;
    }

    public void setStartYearPopulation(Long startYearPopulation) {
        this.startYearPopulation = startYearPopulation;
    }
}
