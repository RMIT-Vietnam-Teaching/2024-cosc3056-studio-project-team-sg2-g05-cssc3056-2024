package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.dto.country.request.CountryRequestDTO;
import com.xuanduy.terrascope.dto.country.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.Country;
import com.xuanduy.terrascope.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAllCountry();
    }

    public List<CountryRecordDTO> getAllCountriesRecords() {
        return countryRepository.findAllCountryRecords();
    }



    public List<RawAndPercentageChangeCountryRecordDTO> getAllRawAndPercentageChangeCountryRecords
            (int startYear, int endYear, String sortBy, String sortOrder) {
        return countryRepository.findAllRawAndPercentageChangeCountryRecords(startYear, endYear, sortBy, sortOrder);
    }

    public List<RawAndPercentageChangeCountryRecordDTO> getAllRawAndPercentageChangeCountryRecordsByIds(
            int startYear,
            int endYear,
            List<String> countryIds) {
        return countryRepository.findAllRawAndPercentageChangeRecordsByIds(startYear, endYear, countryIds);
    }

    public YearRangeDTO getTemperatureYearRange() {
        return countryRepository.findTemperatureYearRange();
    }


    public YearRangeDTO getPopulationYearRange() {
        return countryRepository.findPopulationYearRange();
    }

    public YearRangeDTO getYearRange() {
        return countryRepository.findYearRange();
    }

    public YearRangeDTO getTemperatureYearRangeByCountryId(int countryId) {
        return countryRepository.findTemperatureYearRangeByCountryId(countryId);
    }

    public YearRangeDTO getPopulationYearRangeByCountryId(int countryId) {
        return countryRepository.findPopulationYearRangeByCountryId(countryId);
    }

    public List<CountryDataWithinPeriodDTO> getAverageValuesOfTemperatureCountryRecords(
            List<String> countryIds, List<Integer> startYears, int yearLength) {
        return countryRepository.findAverageValuesOfTemperatureCountryRecords(countryIds, startYears, yearLength);
    }

    public List<Integer> getTemperatureUnavailableYearsByCountryId(
            int countryId, int startYear, int endYear) {
        return countryRepository.findTemperatureUnavailableYearsByCountryId(countryId, startYear, endYear);
    }

    public List<Integer> getPopulationUnavailableYearsByCountryId(
            int countryId, int startYear, int endYear) {
        return countryRepository.findPopulationUnavailableYearsByCountryId(countryId, startYear, endYear);
    }

    public List<SimilarPeriodCountryRecordDTO> getSimilarPeriodsByCountryId(
            int countryId, int startYear, int yearLength, int limit) {
        return countryRepository.findSimilarPeriodsByCountryId(countryId, startYear, yearLength, limit);
    }
}
