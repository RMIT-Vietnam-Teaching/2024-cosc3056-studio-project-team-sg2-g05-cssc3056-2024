package com.xuanduy.terrascope.model;

public class GlobalRecord {
    int year;
    Double averageTemperature;
    Double maxTemperature;
    Double minTemperature;
    Double landOceanAverageTemperature;
    Double landOceanMaxTemperature;
    Double landOceanMinTemperature;
    Long population;

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Double getLandOceanAverageTemperature() {
        return landOceanAverageTemperature;
    }

    public void setLandOceanAverageTemperature(Double landOceanAverageTemperature) {
        this.landOceanAverageTemperature = landOceanAverageTemperature;
    }

    public Double getLandOceanMaxTemperature() {
        return landOceanMaxTemperature;
    }

    public void setLandOceanMaxTemperature(Double landOceanMaxTemperature) {
        this.landOceanMaxTemperature = landOceanMaxTemperature;
    }

    public Double getLandOceanMinTemperature() {
        return landOceanMinTemperature;
    }

    public void setLandOceanMinTemperature(Double landOceanMinTemperature) {
        this.landOceanMinTemperature = landOceanMinTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
