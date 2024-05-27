package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.dto.global.respond.RawAndPercentageChangeGlobalRecordDTO;
import com.xuanduy.terrascope.dto.global.respond.WorldYearRangeDTO;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.GlobalRecord;
import com.xuanduy.terrascope.repository.GlobalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalService {
    @Autowired
    private GlobalRepository globalRepository;


    public RawAndPercentageChangeGlobalRecordDTO getRawAndPercentageChangeGlobalRecord(
            int startYear, int endYear) {
        return globalRepository.findRawAndPercentageChangeGlobalRecord(startYear, endYear);
    }

    public YearRangeDTO getYearRangeGlobalRecord() {
        return globalRepository.findYearRangeGlobalRecord();
    }

    public WorldYearRangeDTO getPopulationYearRange() {
        return globalRepository.findPopulationYearRange();
    }

    public WorldYearRangeDTO getTemperatureYearRange() {
        return globalRepository.findTemperatureYearRange();
    }

    public List<GlobalRecord> getAllGlobalRecords() {
        return globalRepository.findAllGlobalRecords();
    }
}
