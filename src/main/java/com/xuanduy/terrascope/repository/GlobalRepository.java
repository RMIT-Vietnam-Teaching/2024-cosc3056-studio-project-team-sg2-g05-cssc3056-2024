package com.xuanduy.terrascope.repository;

import com.xuanduy.terrascope.dto.country.respond.RawAndPercentageChangeCountryRecordDTO;
import com.xuanduy.terrascope.dto.global.respond.RawAndPercentageChangeGlobalRecordDTO;
import com.xuanduy.terrascope.dto.global.respond.WorldYearRangeDTO;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.GlobalRecord;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GlobalRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";


    public RawAndPercentageChangeGlobalRecordDTO findRawAndPercentageChangeGlobalRecord(int startYear, int endYear) {
        RawAndPercentageChangeGlobalRecordDTO rawAndPercentageChangeGlobalRecordDTO = new RawAndPercentageChangeGlobalRecordDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
                    SELECT
                         gr1.year AS start_year,
                         gr1.average_temperature AS start_year_average_temperature,
              
                         gr1.population AS start_year_population,
                         gr2.year AS end_year,
                    
                         gr2.average_temperature AS end_year_average_temperature,
                         gr2.population AS end_year_population,
                    
                         gr2.average_temperature - gr1.average_temperature
                             AS raw_value_change_average_temperature,
                         gr2.population - gr1.population
                             AS raw_value_change_population,
                         (gr2.average_temperature - gr1.average_temperature) / gr1.average_temperature
                             AS percentage_change_average_temperature,
                         (CAST(gr2.population AS Double) - CAST(gr1.population AS Double)) / CAST(gr1.population AS Double)
                             AS percentage_change_population
                    FROM
                         global_record gr1
                    LEFT JOIN
                         global_record gr2
                    WHERE
                         gr1.year = {startYear} AND gr2.year = {endYear}
                    """.replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                rawAndPercentageChangeGlobalRecordDTO.setStartYear(startYear);
                rawAndPercentageChangeGlobalRecordDTO.setEndYear(endYear);
                if (results.getObject("start_year_average_temperature") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setStartYearTemperature(results.getDouble("start_year_average_temperature"));
                if (results.getObject("end_year_average_temperature") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setEndYearTemperature(results.getDouble("end_year_average_temperature"));
                if (results.getObject("start_year_population") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setStartYearPopulation(results.getLong("start_year_population"));
                if (results.getObject("end_year_population") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setEndYearPopulation(results.getLong("end_year_population"));
                if (results.getObject("raw_value_change_average_temperature") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setRawValueChangeTemperature(results.getDouble("raw_value_change_average_temperature"));
                if (results.getObject("percentage_change_average_temperature") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setPercentageChangeTemperature(results.getDouble("percentage_change_average_temperature"));
                if (results.getObject("raw_value_change_population") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setRawValueChangePopulation(results.getLong("raw_value_change_population"));
                if (results.getObject("percentage_change_population") != null)
                    rawAndPercentageChangeGlobalRecordDTO.setPercentageChangePopulation(results.getDouble("percentage_change_population"));
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findRawAndPercentageChangeGlobalRecord method in GlobalRepository class");
            e.printStackTrace();

        }
        return rawAndPercentageChangeGlobalRecordDTO;
    }

    public YearRangeDTO findYearRangeGlobalRecord() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
                    SELECT
                         MIN(year) AS min_year,
                         MAX(year) AS max_year
                    FROM
                         global_record
                    """;
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findYearRangeGlobalRecord method in GlobalRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }

    public WorldYearRangeDTO findPopulationYearRange() {
        WorldYearRangeDTO worldYearRangeDTO = new WorldYearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
                    SELECT
                        MIN(year) as min_year,
                        MAX(year) as max_year,
                        MAX(year) - MIN(year) as number_of_years
                    FROM
                        global_record
                    WHERE population IS NOT NULL
                    """;
            ResultSet results = statement.executeQuery(query);
            if(results.next()){
                worldYearRangeDTO.setMinYear(results.getInt("min_year"));
                worldYearRangeDTO.setMaxYear(results.getInt("max_year"));
                worldYearRangeDTO.setNumberOfYears(results.getInt("number_of_years"));
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findPopulationYearRange method in GlobalRepository class");
            e.printStackTrace();
        }
        return worldYearRangeDTO;
    }

    public WorldYearRangeDTO findTemperatureYearRange() {
        WorldYearRangeDTO worldYearRangeDTO = new WorldYearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
                    SELECT
                        MIN(year) as min_year,
                        MAX(year) as max_year,
                        MAX(year) - MIN(year) as number_of_years
                    FROM
                        global_record
                    WHERE average_temperature IS NOT NULL
                    """;
            ResultSet results = statement.executeQuery(query);
            if(results.next()){
                worldYearRangeDTO.setMinYear(results.getInt("min_year"));
                worldYearRangeDTO.setMaxYear(results.getInt("max_year"));
                worldYearRangeDTO.setNumberOfYears(results.getInt("number_of_years"));
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findTemperatureYearRange method in GlobalRepository class");
            e.printStackTrace();
        }
        return worldYearRangeDTO;
    }

    public List<GlobalRecord> findAllGlobalRecords() {
        List<GlobalRecord> globalRecords = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
                    SELECT
                        year,
                        average_temperature,
                        maximum_temperature,
                        minimum_temperature,
                        land_ocean_average_temperature,
                        land_ocean_maximum_temperature,
                        land_ocean_minimum_temperature,
                        population
                    FROM
                        global_record
                    """;
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                GlobalRecord globalRecord = new GlobalRecord();
                globalRecord.setYear(results.getInt("year"));
                if(results.getObject("average_temperature") != null)
                    globalRecord.setAverageTemperature(results.getDouble("average_temperature"));
                if(results.getObject("maximum_temperature") != null)
                    globalRecord.setMaxTemperature(results.getDouble("maximum_temperature"));
                if(results.getObject("minimum_temperature") != null)
                    globalRecord.setMinTemperature(results.getDouble("minimum_temperature"));
                if(results.getObject("land_ocean_average_temperature") != null)
                    globalRecord.setLandOceanAverageTemperature(results.getDouble("land_ocean_average_temperature"));
                if(results.getObject("land_ocean_maximum_temperature") != null)
                    globalRecord.setLandOceanMaxTemperature(results.getDouble("land_ocean_maximum_temperature"));
                if(results.getObject("land_ocean_minimum_temperature") != null)
                    globalRecord.setLandOceanMinTemperature(results.getDouble("land_ocean_minimum_temperature"));
                if(results.getObject("population") != null)globalRecord.setPopulation(results.getLong("population"));
                globalRecords.add(globalRecord);
            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllGlobalRecords method in GlobalRepository class");
            e.printStackTrace();
        }
        return globalRecords;
    }
}
