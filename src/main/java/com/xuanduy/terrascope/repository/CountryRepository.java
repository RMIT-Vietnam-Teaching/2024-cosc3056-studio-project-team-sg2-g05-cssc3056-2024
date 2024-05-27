package com.xuanduy.terrascope.repository;


import com.xuanduy.terrascope.dto.city.respond.CityDTO;
import com.xuanduy.terrascope.dto.city.respond.SimilarPeriodCityRecordDTO;
import com.xuanduy.terrascope.dto.country.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import com.xuanduy.terrascope.model.Country;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CountryRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";

    public List<Country> findAllCountry() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = "SELECT * FROM country";
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                Country country = new Country();
                country.setId(results.getLong("id"));
                country.setName(results.getString("name"));
                country.setCode(results.getString("code"));
                countries.add(country);
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllCountry method in CountryRepository class");
            e.printStackTrace();
        }
        return countries;
    }

    public List<CountryRecordDTO> findAllCountryRecords() {
        List<CountryRecordDTO> countryRecordDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT id, name, code,  year, population, average_temperature
                        FROM country
                        JOIN country_record
                        ON id = country_id
                    """;
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                CountryRecordDTO countryRecordDTO = new CountryRecordDTO();
                countryRecordDTO.setId(results.getInt("id"));
                countryRecordDTO.setName(results.getString("name"));
                countryRecordDTO.setCode(results.getString("code"));
                countryRecordDTO.setYear(results.getInt("year"));
                countryRecordDTO.setPopulation(results.getLong("population"));
                countryRecordDTO.setAverageTemperature(results.getDouble("average_temperature"));
                countryRecordDTOs.add(countryRecordDTO);
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllRecords method in CountryRepository class");
            e.printStackTrace();
        }
        return countryRecordDTOs;
    }
    public YearRangeDTO findPopulationYearRangeByCountryId(long countryId) {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                SELECT
                    MIN(year) AS min_year,
                    MAX(year) AS max_year
                FROM
                    country_record
                WHERE
                    population IS NOT NULL
                    AND country_id = {countryId}
                """.replace("{countryId}", String.valueOf(countryId));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findPopulationYearRangeByCountryId method in CountryRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }

    public YearRangeDTO findTemperatureYearRangeByCountryId(long countryId) {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                SELECT
                    MIN(year) AS min_year,
                    MAX(year) AS max_year
                FROM
                    country_record
                WHERE
                    average_temperature IS NOT NULL
                    AND country_id = {countryId}
                """.replace("{countryId}", String.valueOf(countryId));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            if(results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findTemperatureYearRangeByCountryId method in CountryRepository class");
            e.printStackTrace();

        }
        return yearRangeDTO;
    }

    public List<RawAndPercentageChangeCountryRecordDTO> findAllRawAndPercentageChangeCountryRecords(
            int startYear,
            int endYear,
            String sortBy,
            String sortOrder) {
        List<RawAndPercentageChangeCountryRecordDTO> RawAndPercentageChangeCountryRecordDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String sortQuery = switch (sortBy) {
                case "TemperatureRawValueChange" -> "raw_value_change_average_temperature";
                case "PopulationRawValueChange" -> "raw_value_change_population";
                case "TemperaturePercentageChange" -> "percentage_change_average_temperature";
                case "PopulationPercentageChange" -> "percentage_change_population";
                default -> throw new IllegalStateException("Unexpected value: " + sortBy);
            };

            String query = """
                SELECT
                    country.name, country.code, country.id,
               
                    cr1.average_temperature AS start_year_average_temperature,
                    cr1.population AS start_year_population,
              
                    cr2.average_temperature AS end_year_average_temperature,
                    cr2.population AS end_year_population,
              
                    ROUND(cr2.average_temperature - cr1.average_temperature, 5)
                        AS raw_value_change_average_temperature,
                    cr2.population - cr1.population
                        AS raw_value_change_population,
              
                    (cr2.average_temperature - cr1.average_temperature) / ABS(cr1.average_temperature)
                        AS percentage_change_average_temperature,
                    (CAST(cr2.population AS Double) - CAST(cr1.population AS Double)) / ABS(CAST(cr1.population AS Double))
                        AS percentage_change_population
                FROM
                   country
                LEFT JOIN
                   country_record cr1 ON country.id = cr1.country_id AND cr1.year = {startYear}
                LEFT JOIN
                   country_record cr2 ON country.id = cr2.country_id AND cr2.year = {endYear}
                ORDER BY
                   CASE WHEN {sortQuery} IS NULL THEN 1 ELSE 0 END,
                   {sortQuery} {sortOrder},
                   country.name
               """.replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear))
                    .replace("{sortQuery}", sortQuery)
                    .replace("{sortOrder}", sortOrder);
            ResultSet results = statement.executeQuery(query);
            int number = 1;
            while (results.next()) {

                RawAndPercentageChangeCountryRecordDTO rawAndPercentageChangeCountryRecordDTO = new RawAndPercentageChangeCountryRecordDTO();
                rawAndPercentageChangeCountryRecordDTO.setNumber(number++);

                rawAndPercentageChangeCountryRecordDTO.setId(results.getInt("id"));
                rawAndPercentageChangeCountryRecordDTO.setName(results.getString("name"));
                rawAndPercentageChangeCountryRecordDTO.setCode(results.getString("code"));
                rawAndPercentageChangeCountryRecordDTO.setStartYear(startYear);
                rawAndPercentageChangeCountryRecordDTO.setEndYear(endYear);
                if (results.getObject("start_year_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setStartYearTemperature(results.getDouble("start_year_average_temperature"));
                if (results.getObject("end_year_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setEndYearTemperature(results.getDouble("end_year_average_temperature"));
                if (results.getObject("start_year_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setStartYearPopulation(results.getInt("start_year_population"));
                if (results.getObject("end_year_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setEndYearPopulation(results.getInt("end_year_population"));
                if (results.getObject("raw_value_change_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setRawValueChangeTemperature((double) results.getFloat("raw_value_change_average_temperature"));
                if (results.getObject("percentage_change_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setPercentageChangeTemperature(results.getDouble("percentage_change_average_temperature"));
                if (results.getObject("raw_value_change_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setRawValueChangePopulation(results.getInt("raw_value_change_population"));
                if (results.getObject("percentage_change_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setPercentageChangePopulation(results.getDouble("percentage_change_population"));
                RawAndPercentageChangeCountryRecordDTOs.add(rawAndPercentageChangeCountryRecordDTO);
            }

            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllRawAndPercentageChangeRecords method in CountryRepository class");
            e.printStackTrace();
        }
        return RawAndPercentageChangeCountryRecordDTOs;
    }

    public YearRangeDTO findPopulationYearRange() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                SELECT
                    MIN(year) AS min_year,
                    MAX(year) AS max_year
                FROM
                    country_record
                WHERE
                    population IS NOT NULL
            """;

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findPopulationYearRange method in CountryRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }
    public List<RawAndPercentageChangeCountryRecordDTO> findAllRawAndPercentageChangeRecordsByIds(
            int startYear, int endYear, List<String> countryIds) {
        List<RawAndPercentageChangeCountryRecordDTO> RawAndPercentageChangeCountryRecordDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            Statement statement = connection.createStatement();
            String query = """
               SELECT country.name, country.code, country.id,
               
                      cr1.year AS start_year,
                      cr1.average_temperature AS start_year_average_temperature,
                      cr1.population AS start_year_population,
               
                      cr2.year AS end_year,
                      cr2.average_temperature AS end_year_average_temperature,
                      cr2.population AS end_year_population,
               
                      cr2.average_temperature - cr1.average_temperature
                        AS raw_value_change_average_temperature,
                      cr2.population - cr1.population
                        AS raw_value_change_population,
               
                      (cr2.average_temperature - cr1.average_temperature) / cr1.average_temperature
                        AS percentage_change_average_temperature,
                      (CAST(cr2.population AS Double) - CAST(cr1.population AS Double)) / CAST(cr1.population AS Double)
                        AS percentage_change_population
               FROM
                    country
               LEFT JOIN
                    country_record cr1 ON country.id = cr1.country_id AND cr1.year = {startYear}
               LEFT JOIN
                    country_record cr2 ON country.id = cr2.country_id AND cr2.year = {endYear}
               WHERE
                    country.id IN ({countryIds});
               """.replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear))
                    .replace("{countryIds}", String.join((CharSequence) ",", countryIds));
            ResultSet results = statement.executeQuery(query);

            int number = 1;
            while (results.next()) {
                RawAndPercentageChangeCountryRecordDTO rawAndPercentageChangeCountryRecordDTO = new RawAndPercentageChangeCountryRecordDTO();
                rawAndPercentageChangeCountryRecordDTO.setId(results.getInt("id"));
                rawAndPercentageChangeCountryRecordDTO.setNumber(number++);
                rawAndPercentageChangeCountryRecordDTO.setName(results.getString("name"));
                rawAndPercentageChangeCountryRecordDTO.setCode(results.getString("code"));
                rawAndPercentageChangeCountryRecordDTO.setStartYear(startYear);
                rawAndPercentageChangeCountryRecordDTO.setEndYear(endYear);
                if (results.getObject("start_year_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setStartYearTemperature(results.getDouble("start_year_average_temperature"));
                if (results.getObject("end_year_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setEndYearTemperature(results.getDouble("end_year_average_temperature"));
                if (results.getObject("start_year_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setStartYearPopulation(results.getInt("start_year_population"));
                if (results.getObject("end_year_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setEndYearPopulation(results.getInt("end_year_population"));
                if (results.getObject("raw_value_change_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setRawValueChangeTemperature(results.getDouble("raw_value_change_average_temperature"));
                if (results.getObject("percentage_change_average_temperature") != null)
                    rawAndPercentageChangeCountryRecordDTO.setPercentageChangeTemperature(results.getDouble("percentage_change_average_temperature"));
                if (results.getObject("raw_value_change_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setRawValueChangePopulation(results.getInt("raw_value_change_population"));
                if (results.getObject("percentage_change_population") != null)
                    rawAndPercentageChangeCountryRecordDTO.setPercentageChangePopulation(results.getDouble("percentage_change_population"));


                RawAndPercentageChangeCountryRecordDTOs.add(rawAndPercentageChangeCountryRecordDTO);

            }
            results.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllRawAndPercentageChangeRecordsByIds method in CountryRepository class");
            e.printStackTrace();
        }
        return RawAndPercentageChangeCountryRecordDTOs;
    }

    public YearRangeDTO findTemperatureYearRange() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                SELECT
                    MIN(year) AS min_year,
                    MAX(year) AS max_year
                FROM
                    country_record
                WHERE average_temperature IS NOT NULL
            """;

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findYearRange method in CountryRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }



    public YearRangeDTO findYearRange() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                SELECT
                    MIN(year) AS min_year,
                    MAX(year) AS max_year
                FROM
                    country_record
            """;

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findYearRange method in CountryRepository class");
            e.printStackTrace();
    }
        return yearRangeDTO;
    }

    public List<CountryDataWithinPeriodDTO> findAverageValuesOfTemperatureCountryRecords(
            List<String> countryIds,
            List<Integer> startYears,
            int yearLengths
    ) {
        List<CountryDataWithinPeriodDTO> countryDataWithinPeriodDTOS = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)){
            for(int startYear: startYears){
                String query = """
                            SELECT country.id, country.name,
                                   AVG(country_record.average_temperature)
                                       AS average_value_of_temperature,
                                   COUNT(country_record.average_temperature)
                                       AS available_year_count
                            FROM
                                country
                            LEFT JOIN
                                country_record ON country.id = country_record.country_id
                                    AND country_record.year BETWEEN {startYear} AND {endYear}
                            WHERE
                                country.id IN ({countryIds})
                            GROUP BY
                                country.id
                            ORDER BY
                                Case When AVG(country_record.average_temperature) IS NULL THEN 1 ELSE 0 END,
                                average_value_of_temperature ASC
                            """.replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(startYear + yearLengths - 1))
                        .replace("{countryIds}", String.join(",", countryIds));
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet results = statement.executeQuery(query);
                List<AverageValueOfTemperatureCountryRecordDTO> averageValueOfTemperatureCountryRecordDTOs
                        = new ArrayList<>();
                CountryDataWithinPeriodDTO countryDataWithinPeriodDTO = new CountryDataWithinPeriodDTO();
                countryDataWithinPeriodDTO.setStartYear(startYear);
                countryDataWithinPeriodDTO.setYearLength(yearLengths);
                int number = 1;
                while(results.next()){
                    AverageValueOfTemperatureCountryRecordDTO averageValueOfTemperatureCountryRecordDTO
                            = new AverageValueOfTemperatureCountryRecordDTO();
                    averageValueOfTemperatureCountryRecordDTO.setId(results.getLong("id"));
                    averageValueOfTemperatureCountryRecordDTO.setNumber(number++);
                    averageValueOfTemperatureCountryRecordDTO.setName(results.getString("name"));
                    averageValueOfTemperatureCountryRecordDTO.setStartYear(startYear);
                    averageValueOfTemperatureCountryRecordDTO.setEndYear(startYear + yearLengths - 1);
                    if(results.getObject("average_value_of_temperature") != null){
                        averageValueOfTemperatureCountryRecordDTO.setAverageValueOfTemperature(results.getDouble("average_value_of_temperature"));
                    }
                    if(results.getObject("available_year_count") != null){
                        averageValueOfTemperatureCountryRecordDTO.setAvailableYearCount(results.getInt("available_year_count"));
                    }
                    averageValueOfTemperatureCountryRecordDTOs.add(averageValueOfTemperatureCountryRecordDTO);
                }
                countryDataWithinPeriodDTO.setAverageValueOfTemperatureCountryRecordDTOs(averageValueOfTemperatureCountryRecordDTOs);
                countryDataWithinPeriodDTOS.add(countryDataWithinPeriodDTO);
            }

        }
        catch (Exception e) {
            System.err.println("Error in findAverageValuesOfTemperatureCountryRecords method in CountryRepository class");
            e.printStackTrace();
        }
        return countryDataWithinPeriodDTOS;
    };

    public List<Integer> findTemperatureUnavailableYearsByCountryId(
            int countryId, int startYear, int endYear) {
        List<Integer> unavailableYears = new ArrayList<>();
        YearRangeDTO yearRangeDTO = findTemperatureYearRangeByCountryId(countryId);
        if(endYear < yearRangeDTO.getMinYear()) return unavailableYears;
        if(startYear > yearRangeDTO.getMaxYear()) return unavailableYears;
        startYear = Math.max(startYear, yearRangeDTO.getMinYear());
        endYear = Math.min(endYear, yearRangeDTO.getMaxYear());
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    WITH RECURSIVE all_years(year) AS (
                        SELECT {startYear}
                        UNION ALL
                        SELECT year + 1 FROM all_years WHERE year < {endYear}
                    )
                    SELECT all_years.year
                    FROM all_years
                    LEFT JOIN (
                        SELECT DISTINCT year
                        FROM country_record
                        WHERE country_id = {countryId} AND year BETWEEN {startYear} AND {endYear}
                            AND average_temperature IS NOT NULL
                    ) AS years ON all_years.year = years.year
                    WHERE years.year IS NULL ;
                    """.replace("{countryId}", String.valueOf(countryId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                unavailableYears.add(results.getInt("year"));
                }
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findTemperatureUnavailableYearsByCountryId method in CountryRepository class");
            e.printStackTrace();
            }
        return unavailableYears;
        }

    public List<Integer> findPopulationUnavailableYearsByCountryId(
            int countryId, int startYear, int endYear) {
        List<Integer> unavailableYears = new ArrayList<>();
        YearRangeDTO yearRangeDTO = findPopulationYearRangeByCountryId(countryId);

        if(endYear < yearRangeDTO.getMinYear()) return unavailableYears;
        if(startYear > yearRangeDTO.getMaxYear()) return unavailableYears;

        startYear = Math.max(startYear, yearRangeDTO.getMinYear());
        endYear = Math.min(endYear, yearRangeDTO.getMaxYear());
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    WITH RECURSIVE all_years(year) AS (
                        SELECT {startYear}
                        UNION ALL
                        SELECT year + 1 FROM all_years WHERE year < {endYear}
                    )
                    SELECT all_years.year
                    FROM all_years
                    LEFT JOIN (
                        SELECT DISTINCT year
                        FROM country_record
                        WHERE country_id = {countryId} AND year BETWEEN {startYear} AND {endYear}
                            AND population IS NOT NULL
                    ) AS years ON all_years.year = years.year
                    WHERE years.year IS NULL ;
                    """.replace("{countryId}", String.valueOf(countryId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                unavailableYears.add(results.getInt("year"));
            }
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findPopulationUnavailableYearsByCountryId method in CountryRepository class");
            e.printStackTrace();
        }
        return unavailableYears;
    }

    public List<SimilarPeriodCountryRecordDTO> findSimilarPeriodsByCountryId(
            int countryId, int startYear, int yearLength, int limit) {
        List<SimilarPeriodCountryRecordDTO> similarPeriodCountryRecordDTOs = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        country.name AS name,
                        country.id AS id,
                        temp_data.start_year,
                        temp_data.average_temperature,
                        temp_data.temperature_difference
                    FROM
                        country
                    JOIN
                    (
                        WITH temperature_data AS (
                            SELECT
                                country_id,
                                year AS start_year,
                                ROUND(AVG(average_temperature) OVER (
                                    PARTITION BY country_id
                                    ORDER BY year
                                    RANGE BETWEEN CURRENT ROW AND {yearLength} - 1 FOLLOWING
                                ), 5) AS average_temperature
                            FROM
                                country_record
                            WHERE
                                year <= {maxYear} - {yearLength} +1
                        ), reference_period AS (
                            SELECT
                                AVG(average_temperature) AS reference_temperature
                            FROM
                                country_record
                            WHERE
                                country_id = {countryId} AND year BETWEEN {startYear} AND {endYear}
                            GROUP BY
                                country_id
                        )
                        SELECT
                            country_id,
                            start_year,
                            average_temperature,
                            ABS(average_temperature - (SELECT reference_temperature FROM reference_period))
                                AS temperature_difference
                        FROM
                            temperature_data
                    ) AS temp_data
                    ON
                        country.id = temp_data.country_id
                    ORDER BY
                        temp_data.temperature_difference
                    LIMIT {limit}
                    """.replace("{yearLength}", String.valueOf(yearLength))
                    .replace("{countryId}", String.valueOf(countryId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(startYear + yearLength - 1))
                    .replace("{limit}", String.valueOf(limit))
                    .replace("{maxYear}", String.valueOf(findYearRange().getMaxYear())
                    );
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            int number = 1;
            while (results.next()) {
                SimilarPeriodCountryRecordDTO similarPeriodCountryRecordDTO = new SimilarPeriodCountryRecordDTO();
                similarPeriodCountryRecordDTO.setId(results.getInt("id"));
                similarPeriodCountryRecordDTO.setName(results.getString("name"));
                similarPeriodCountryRecordDTO.setStartYear(results.getInt("start_year"));
                similarPeriodCountryRecordDTO.setEndYear(results.getInt("start_year") + yearLength - 1);
                similarPeriodCountryRecordDTO.setAverageValueOfTemperature(results.getDouble("average_temperature"));
                similarPeriodCountryRecordDTO.setTemperatureDifference(results.getDouble("temperature_difference"));
                similarPeriodCountryRecordDTO.setAvailableYearCount(findAvailableYearCountByCountryId(similarPeriodCountryRecordDTO.getId(),
                        similarPeriodCountryRecordDTO.getStartYear(), similarPeriodCountryRecordDTO.getEndYear()));
                if (results.getInt("id") == countryId && results.getInt("start_year") == startYear) {
                    similarPeriodCountryRecordDTO.setNumber(0);
                    similarPeriodCountryRecordDTOs.add(0, similarPeriodCountryRecordDTO);
                    continue;
                };
                similarPeriodCountryRecordDTO.setNumber(number++);
                similarPeriodCountryRecordDTOs.add(similarPeriodCountryRecordDTO);
            }
        }
        catch (Exception e) {
            System.err.println("Error in findSimilarPeriodsByCountryId method in CountryRepository class");
            e.printStackTrace();
        }
        return similarPeriodCountryRecordDTOs;

    }

    private int findAvailableYearCountByCountryId(int id, int startYear, int endYear) {
        int availableYearCount = 0;
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        COUNT(DISTINCT year) AS available_year_count
                    FROM
                        country_record
                    WHERE
                        country_id = {id}
                        AND year BETWEEN {startYear} AND {endYear}
                        AND average_temperature IS NOT NULL
                    """.replace("{id}", String.valueOf(id))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                availableYearCount = results.getInt("available_year_count");
            }
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAvailableYearCountByCountryId method in CountryRepository class");
            e.printStackTrace();
        }
        return availableYearCount;
    }
}
