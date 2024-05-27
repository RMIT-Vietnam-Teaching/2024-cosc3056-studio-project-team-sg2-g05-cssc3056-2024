package com.xuanduy.terrascope.dto.country.respond;

import java.util.List;

public class CountryDataWithinPeriodDTO {
    private int startYear;
    private int yearLength;
    private List<AverageValueOfTemperatureCountryRecordDTO>
                averageValueOfTemperatureCountryRecordDTOs;

    public List<AverageValueOfTemperatureCountryRecordDTO> getAverageValueOfTemperatureCountryRecordDTOs() {
        return averageValueOfTemperatureCountryRecordDTOs;
    }

    public void setAverageValueOfTemperatureCountryRecordDTOs(List<AverageValueOfTemperatureCountryRecordDTO> averageValueOfTemperatureCountryRecordDTOList) {
        this.averageValueOfTemperatureCountryRecordDTOs = averageValueOfTemperatureCountryRecordDTOList;
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
