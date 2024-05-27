package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.dto.state.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<StateDTO> getAllStates() {
        return StateRepository.findAllStates();
    }

    public YearRangeDTO getYearRangeStateRecord() {
        return StateRepository.findYearRangeStateRecord();
    }





    public List<TemperatureChangeStateRecordDTO> getTemperatureChangeStateRecordsByCountry(
            int startYear, int endYear, int countryId, String typeOfTemperature) {
        return stateRepository.findTemperatureChangeStateRecordsByCountryId(startYear, endYear, countryId, typeOfTemperature);
    }

    public YearRangeDTO getStateYearRangeById(int stateId) {
        return stateRepository.findStateYearRangeById(stateId);
    }

    public List<Integer> getUnavailableYearsByStateId(
            int stateId, int startYear, int endYear) {
        return stateRepository.findUnavailableYearsByStateId(stateId, startYear, endYear);
    }

    public List<SimilarPeriodStateRecordDTO> getSimilarPeriodsByStateId(
            int stateId, int startYear, int yearLength, int limit) {
        return stateRepository.findSimilarPeriodsByStateId(stateId, startYear, yearLength, limit);
    }


    public List<StateDataWithinPeriodDTO> getAverageValuesOfTemperatureStateRecords(
            List<Integer> stateIds, List<Integer> startYears, int yearLength) {
        return stateRepository.findAverageValuesOfTemperatureStateRecords(stateIds, startYears, yearLength);
    }
}
