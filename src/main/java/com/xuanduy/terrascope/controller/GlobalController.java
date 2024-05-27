package com.xuanduy.terrascope.controller;


import com.xuanduy.terrascope.dto.global.respond.RawAndPercentageChangeGlobalRecordDTO;
import com.xuanduy.terrascope.dto.global.respond.WorldYearRangeDTO;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.GlobalRecord;
import com.xuanduy.terrascope.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/global")
public class GlobalController {
    @Autowired
    private GlobalService globalService;

    @GetMapping(value = "/records")
    public List<GlobalRecord> getAllGlobalRecords() {
        return globalService.getAllGlobalRecords();
    }
    @GetMapping(value = "records/raw-and-percentage-change")
    public RawAndPercentageChangeGlobalRecordDTO getRawAndPercentageChangeGlobalRecord(@RequestParam int startYear, @RequestParam int endYear) {
        return globalService.getRawAndPercentageChangeGlobalRecord(startYear, endYear);
    }
    @GetMapping(value = "records/population-year-range")
    public WorldYearRangeDTO PopulationYearRange(){
        return globalService.getPopulationYearRange();
    }
    @GetMapping(value = "records/temperature-year-range")
    public WorldYearRangeDTO getTemperatureYearRange(){
        return globalService.getTemperatureYearRange();
    }
    @GetMapping(value = "records/year-range")
    public YearRangeDTO getYearRangeGlobalRecord(){
        return globalService.getYearRangeGlobalRecord();
    }

}
