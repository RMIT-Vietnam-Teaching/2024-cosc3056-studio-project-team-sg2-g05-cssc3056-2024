package com.xuanduy.terrascope.dto.state.respond;

import com.xuanduy.terrascope.dto.country.respond.AverageValueOfTemperatureCountryRecordDTO;

import java.util.List;

public class StateDataWithinPeriodDTO {
    private int startYear;
    private int yearLength;
    private List<AverageValueOfTemperatureStateRecordDTO>
            AverageValueOfTemperatureStateRecordDTOs;

    public List<AverageValueOfTemperatureStateRecordDTO> getAverageValueOfTemperatureStateRecordDTOs() {
        return AverageValueOfTemperatureStateRecordDTOs;
    }

    public void setAverageValueOfTemperatureStateRecordDTOs(List<AverageValueOfTemperatureStateRecordDTO> averageValueOfTemperatureStateRecordDTOs) {
        AverageValueOfTemperatureStateRecordDTOs = averageValueOfTemperatureStateRecordDTOs;
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
