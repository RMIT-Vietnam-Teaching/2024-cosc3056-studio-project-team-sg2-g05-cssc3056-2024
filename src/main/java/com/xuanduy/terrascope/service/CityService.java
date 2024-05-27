package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.dto.city.respond.CityDTO;
import com.xuanduy.terrascope.dto.city.respond.CityDataWithinPeriodDTO;
import com.xuanduy.terrascope.dto.city.respond.SimilarPeriodCityRecordDTO;
import com.xuanduy.terrascope.dto.city.respond.TemperatureChangeCityRecordDTO;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> getAllCities() {
        return cityRepository.findAllCity();
    }

    public List<TemperatureChangeCityRecordDTO> getTemperatureChangeCityRecordsByCountry(
            int startYear, int endYear, int countryId, String typeOfTemperature) {
        return cityRepository.findTemperatureChangeCityRecordsByCountryId(startYear, endYear, countryId,
                typeOfTemperature);
    }

    public YearRangeDTO getYearRangeCityRecord() {
        return cityRepository.findYearRangeCityRecord();
    }

    public List<CityDataWithinPeriodDTO> getAverageValuesOfTemperatureCityRecords(
            List<Integer> cityIds,
            List<Integer> startYears,
            int yearLengths) {
        return cityRepository.findAverageValuesOfTemperatureCityRecords(cityIds, startYears, yearLengths);
    }

    public YearRangeDTO getYearRangeByCityId(int cityId) {
        return cityRepository.findYearRangeByCityId(cityId);
    }

    public List<SimilarPeriodCityRecordDTO> getSimilarPeriodsByCityId(
            int cityId, int startYear, int yearLength, int limit) {
        return cityRepository.findSimilarPeriodsByCityId(cityId, startYear, yearLength, limit);
    }

    public List<Integer> getUnavailableYearsByCityId(int cityId, int startYear, int endYear) {
        return cityRepository.findUnavailableYearsByCityId(cityId, startYear, endYear);
    }
}
