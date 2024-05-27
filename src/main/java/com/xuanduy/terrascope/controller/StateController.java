package com.xuanduy.terrascope.controller;



import com.xuanduy.terrascope.dto.state.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping()
    public List<StateDTO> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping(value = "records/year-range")
    public YearRangeDTO getYearRangeStateRecord(){
        return stateService.getYearRangeStateRecord();
    }

    @GetMapping(value = "records/year-range-by-state-id")
    public YearRangeDTO getStateYearRangeById(@RequestParam int stateId){
        return stateService.getStateYearRangeById(stateId);
    }

    @GetMapping(value = "records/unavailable-years-by-state-id")
    public List<Integer> getUnavailableYearsByStateId(
            @RequestParam("stateId") int stateId, @RequestParam("startYear") int startYear
            , @RequestParam("endYear") int endYear){
        return stateService.getUnavailableYearsByStateId(stateId, startYear, endYear);
    }
    @GetMapping(value = "records/temperature-change-by-country-id")
    public List<TemperatureChangeStateRecordDTO> getTemperatureChangeStateRecordsByCountry(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear,
            @RequestParam("countryId") int countryId,
            @RequestParam("typeOfTemperature") String typeOfTemperature
    ){
        return stateService.getTemperatureChangeStateRecordsByCountry(startYear, endYear, countryId, typeOfTemperature);
    }

    @GetMapping(value = "records/similar-periods-by-state-id")
    public List<SimilarPeriodStateRecordDTO> getSimilarPeriodsByStateId(
            @RequestParam("stateId") int stateId,
            @RequestParam("startYear") int startYear,
            @RequestParam("yearLength") int yearLength,
            @RequestParam("limit") int limit
    ){
        return stateService.getSimilarPeriodsByStateId(stateId, startYear, yearLength, limit);
    }

    @GetMapping(value = "records/average-values-of-temperature")
    public List<StateDataWithinPeriodDTO> getAverageValuesOfTemperatureStateRecords(
            @RequestParam("stateIds") List<Integer> stateIds,
            @RequestParam("startYears") List<Integer> startYears,
            @RequestParam("yearLength") int yearLength
    ){
        return stateService.getAverageValuesOfTemperatureStateRecords(stateIds, startYears, yearLength);
    }
}
