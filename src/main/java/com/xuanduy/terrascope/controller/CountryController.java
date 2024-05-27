package com.xuanduy.terrascope.controller;

import com.xuanduy.terrascope.dto.city.respond.CityDataWithinPeriodDTO;
import com.xuanduy.terrascope.dto.city.respond.SimilarPeriodCityRecordDTO;
import com.xuanduy.terrascope.dto.country.request.CountryRequestDTO;
import com.xuanduy.terrascope.dto.country.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.Country;
import com.xuanduy.terrascope.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping(value = "records")
    public List<CountryRecordDTO> getAllCountriesRecords() {
        return countryService.getAllCountriesRecords();
    }

    @GetMapping(value = "records/temperature-year-range")
    public YearRangeDTO getTemperatureYearRange(){
        return countryService.getTemperatureYearRange();
    }

    @GetMapping(value = "records/population-year-range")
    public YearRangeDTO getPopulationYearRange(){
        return countryService.getPopulationYearRange();
    }

    @GetMapping(value = "records/temperature-year-range-by-country-id")
    public YearRangeDTO getTemperatureYearRangeByIds(@RequestParam int countryId){
        return countryService.getTemperatureYearRangeByCountryId(countryId);
    }

    @GetMapping(value = "records/temperature-unavailable-years-by-country-id")
    public List<Integer> getTemperatureUnavailableYearsByCountryId(
            @RequestParam int countryId,
            @RequestParam int startYear,
            @RequestParam int endYear){
        return countryService.getTemperatureUnavailableYearsByCountryId(countryId, startYear, endYear);
    }

    @GetMapping(value = "records/population-unavailable-years-by-country-id")
    public List<Integer> getPopulationUnavailableYearsByCountryId(
            @RequestParam int countryId,
            @RequestParam int startYear,
            @RequestParam int endYear){
        return countryService.getPopulationUnavailableYearsByCountryId(countryId, startYear, endYear);
    }
    @GetMapping(value = "records/population-year-range-by-country-id")
    public YearRangeDTO getPopulationYearRangeByIds(@RequestParam int countryId){
        return countryService.getPopulationYearRangeByCountryId(countryId);
    }

    @GetMapping(value = "records/year-range")
    public YearRangeDTO getYearRange(){
        return countryService.getYearRange();
    }

    @GetMapping(value = "records/raw-and-percentage-change")
    public List<RawAndPercentageChangeCountryRecordDTO> getAllRawAndPercentageChangeCountryRecords(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortOrder") String sortOrder){
        return countryService.getAllRawAndPercentageChangeCountryRecords(startYear, endYear, sortBy, sortOrder);
    }

    @GetMapping(value = "records/raw-and-percentage-change-by-country-ids")
    public List<RawAndPercentageChangeCountryRecordDTO> getAllRawAndPercentageChangeCountryRecordsByIds(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear,
            @RequestParam List<String> countryIds){
        return countryService.getAllRawAndPercentageChangeCountryRecordsByIds(startYear, endYear, countryIds);
    }


    @GetMapping(value = "records/average-values-of-temperature")
    public List<CountryDataWithinPeriodDTO> getAverageValuesOfTemperatureCountryRecords (
            @RequestParam("countryIds") List<String> countryIds,
            @RequestParam("startYears") List<Integer> startYears,
            @RequestParam("yearLength") int yearLength
    ){
        return countryService.getAverageValuesOfTemperatureCountryRecords(countryIds, startYears, yearLength);
    }

    @GetMapping(value = "records/similar-periods-by-country-id")
    public List<SimilarPeriodCountryRecordDTO> getSimilarPeriodsByCountryId(
            @RequestParam("countryId") int countryId,
            @RequestParam("startYear") int startYear,
            @RequestParam("yearLength") int yearLength,
            @RequestParam("limit") int limit
    ){
        return countryService.getSimilarPeriodsByCountryId(countryId, startYear, yearLength, limit);
    }
}