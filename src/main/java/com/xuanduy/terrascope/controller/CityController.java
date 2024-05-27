package com.xuanduy.terrascope.controller;

import com.xuanduy.terrascope.dto.city.respond.CityDTO;
import com.xuanduy.terrascope.dto.city.respond.SimilarPeriodCityRecordDTO;
import com.xuanduy.terrascope.dto.city.respond.TemperatureChangeCityRecordDTO;
import com.xuanduy.terrascope.dto.city.respond.CityDataWithinPeriodDTO;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "")
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping(value = "records/year-range")
    public YearRangeDTO getYearRangeCityRecord(){
    return cityService.getYearRangeCityRecord();
}

    @GetMapping(value = "records/temperature-change-by-country-id")
    public List<TemperatureChangeCityRecordDTO> getTemperatureChangeCityRecordsByCountry(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear,
            @RequestParam("countryId") int countryId,
            @RequestParam("typeOfTemperature") String typeOfTemperature
    ){
        return cityService.getTemperatureChangeCityRecordsByCountry(startYear, endYear, countryId, typeOfTemperature);
    }
    @GetMapping(value = "records/average-values-of-temperature")
    public List<CityDataWithinPeriodDTO> getAverageValuesOfTemperatureCityRecords(
            @RequestParam("cityIds") List<Integer> cityIds,
            @RequestParam("startYears") List<Integer> startYears,
            @RequestParam("yearLength") int yearLength
    ){
        return cityService.getAverageValuesOfTemperatureCityRecords(cityIds, startYears, yearLength);
    }

    @GetMapping(value = "records/year-range-by-city-id")
    public YearRangeDTO getYearRangeByCityId(@RequestParam("cityId") int cityId){
        return cityService.getYearRangeByCityId(cityId);
    }

    @GetMapping(value = "records/similar-periods-by-city-id")
    public List<SimilarPeriodCityRecordDTO> getSimilarPeriodsByCityId(
            @RequestParam("cityId") int cityId,
            @RequestParam("startYear") int startYear,
            @RequestParam("yearLength") int yearLength,
            @RequestParam("limit") int limit
    ){
        return cityService.getSimilarPeriodsByCityId(cityId, startYear, yearLength, limit);
    }

    @GetMapping(value = "records/unavailable-years-by-city-id")
    public List<Integer> getUnavailableYearsByCityId(
            @RequestParam("cityId") int cityId, @RequestParam("startYear") int startYear
    , @RequestParam("endYear") int endYear){
        return cityService.getUnavailableYearsByCityId(cityId, startYear, endYear);
    }
}
