package com.xuanduy.terrascope.dto.city.respond;

import java.util.List;

public class CityDataWithinPeriodDTO {
    private int startYear;
    private int yearLength;
    private List averageValueOfTemperatureCityRecordDTOs;
    public List getAverageValueOfTemperatureCityRecordDTOs() {
        return averageValueOfTemperatureCityRecordDTOs;
    }

    public void setAverageValueOfTemperatureCityRecordDTOs(List averageValueOfTemperatureCityRecordDTOList) {
        this.averageValueOfTemperatureCityRecordDTOs = averageValueOfTemperatureCityRecordDTOList;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getYearLength() {
        return yearLength;
    }

    public void setYearLength(int yearLength) {
        this.yearLength = yearLength;
    }


}
