package com.xuanduy.terrascope.repository;

import com.xuanduy.terrascope.dto.city.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";

    public List<CityDTO> findAllCity() {
        List<CityDTO> CityDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT city.id, city.name, country.name AS country_name,
                        city.longitude, city.latitude
                        FROM
                            city
                        INNER JOIN
                            country
                        ON
                            city.country_id = country.id
                    """;
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                CityDTO cityDTO = new CityDTO();
                cityDTO.setId(results.getLong("id"));
                cityDTO.setName(results.getString("name"));
                cityDTO.setCountryName(results.getString("country_name"));
                cityDTO.setLongitude(results.getString("longitude"));
                cityDTO.setLatitude(results.getString("latitude"));
                CityDTOs.add(cityDTO);
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findAllCity method in CityRepository clASs");
            e.printStackTrace();
        }
        return CityDTOs;
    }
    public YearRangeDTO findYearRangeCityRecord() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        MIN(year) AS min_year,
                        MAX(year) AS max_year
                    FROM
                        city_record
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
            System.err.println("Error in findYearRange method in CityRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }

    public YearRangeDTO findYearRangeByCityId(int cityId) {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        MIN(year) AS min_year,
                        MAX(year) AS max_year
                    FROM
                        city_record
                    WHERE
                        city_id = {cityId}
                    """.replace("{cityId}", String.valueOf(cityId));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                yearRangeDTO.setMinYear(results.getInt("min_year"));
                yearRangeDTO.setMaxYear(results.getInt("max_year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findYearRangeByCityId method in CityRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }
    public List<Integer> findUnavailableYearsByCityId(
            int cityId,
            int startYear,
            int endYear
    ) {
        List<Integer> unavailableYears = new ArrayList<>();
        YearRangeDTO yearRangeDTO = findYearRangeByCityId(cityId);
        if(endYear < yearRangeDTO.getMinYear()) return unavailableYears;
        if(startYear > yearRangeDTO.getMaxYear()) return unavailableYears;

        endYear = Math.min(endYear, yearRangeDTO.getMaxYear());
        startYear = Math.max(startYear, yearRangeDTO.getMinYear());


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
                        FROM city_record
                        WHERE city_id = {cityId} AND year BETWEEN {startYear} AND {endYear}
                    ) AS years ON all_years.year = years.year
                    WHERE years.year IS NULL;
                    """.replace("{cityId}", String.valueOf(cityId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            while (results.next()){
                unavailableYears.add(results.getInt("year"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findUnavailableYearsByCityId method in CityRepository class");
            e.printStackTrace();
        }
        return unavailableYears;
    }

    public CityDTO findCityById(int id) {
        CityDTO cityDTO = new CityDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT city.id, city.name, country.name AS country_name,
                        city.longitude, city.latitude
                        FROM
                            city
                        INNER JOIN
                            country
                        ON
                            city.country_id = country.id
                        WHERE
                            city.id = {id}
                    """.replace("{id}", String.valueOf(id));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                cityDTO.setId(results.getLong("id"));
                cityDTO.setName(results.getString("name"));
                cityDTO.setCountryName(results.getString("country_name"));
                cityDTO.setLongitude(results.getString("longitude"));
                cityDTO.setLatitude(results.getString("latitude"));
            }

            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findCityById method in CityRepository class");
            e.printStackTrace();
        }
        return cityDTO;
    }

    public List<TemperatureChangeCityRecordDTO> findTemperatureChangeCityRecordsByCountryId(
            int startYear, int endYear, int countryId, String typeOfTemperature) {
        List<TemperatureChangeCityRecordDTO> temperatureChangeCityRecordDTOs = new ArrayList<>();
        String temperatureColumn = switch (typeOfTemperature) {
            case "MaxTemperature" -> "maximum_temperature";
            case "MinTemperature" -> "minimum_temperature";
            case "AverageTemperature" -> "average_temperature";
            default -> throw new IllegalStateException("Unexpected value: " + typeOfTemperature);
        };

        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String initialQuery = """
        SELECT
            city_id AS id,
            AVG(percentage_change) AS average_percentage_change,
            COUNT(percentage_change) AS available_year_count
        FROM (
            SELECT
                year,
                city_id,
                average_temperature,
                ({temperatureColumn} - LAG({temperatureColumn}) OVER (PARTITION BY city_id ORDER BY year)) /
                    ABS(LAG({temperatureColumn}) OVER (PARTITION BY city_id ORDER BY year)) 
                        AS percentage_change
            FROM
                city_record
            WHERE
                city_id IN (
                    SELECT
                        id
                    FROM
                        city
                    WHERE
                        country_id = {countryId}
                    )
                AND
                    year BETWEEN {startYear} AND {endYear}
            )
        GROUP BY
            city_id
        ORDER BY
            CASE WHEN AVG(percentage_change) IS NULL THEN 1 ELSE 0 END,
            average_percentage_change DESC
    """.replace("{temperatureColumn}", temperatureColumn)
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear))
                    .replace("{countryId}", String.valueOf(countryId));

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet resultSet = statement.executeQuery(initialQuery);

            int number = 1;
            while (resultSet.next()) {
                TemperatureChangeCityRecordDTO temperatureChangeCityRecordDTO = new TemperatureChangeCityRecordDTO();
                temperatureChangeCityRecordDTO.setId(resultSet.getInt("id"));
                if (resultSet.getObject("average_percentage_change") != null) {
                    temperatureChangeCityRecordDTO.setAveragePercentageChangeTemperature(
                            resultSet.getDouble("average_percentage_change"));
                }
                temperatureChangeCityRecordDTO.setNumber(number++);
                temperatureChangeCityRecordDTO.setAvailableYearCount(
                        resultSet.getInt("available_year_count"));
                String detailQuery = """
                        SELECT
                            city.name, city.longitude, city.latitude,
                            cr1.{temperatureColumn} AS start_year_temperature,
                            cr2.{temperatureColumn} AS end_year_temperature,
                            cr2.{temperatureColumn} - cr1.{temperatureColumn}
                                AS raw_value_change_temperature,
                            (cr2.{temperatureColumn} - cr1.{temperatureColumn}) / ABS(cr1.{temperatureColumn}) 
                                AS percentage_change_temperature
                        FROM
                            city
                        LEFT JOIN
                            city_record AS cr1 
                                ON cr1.city_id = city.id AND cr1.year = {startYear}
                        LEFT JOIN
                            city_record AS cr2 
                                ON cr2.city_id = city.id AND cr2.year = {endYear}
                        WHERE
                            city.id = {cityId}
                    """.replace("{temperatureColumn}", temperatureColumn)
                        .replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(endYear))
                        .replace("{cityId}", String.valueOf(temperatureChangeCityRecordDTO.getId()));

                Statement detailStatement = connection.createStatement();
                ResultSet detailResultSet = detailStatement.executeQuery(detailQuery);
                if (detailResultSet.next()) {
                    temperatureChangeCityRecordDTO.setName(detailResultSet.getString("name"));
                    temperatureChangeCityRecordDTO.setStartYear(startYear);
                    temperatureChangeCityRecordDTO.setEndYear(endYear);
                    temperatureChangeCityRecordDTO.setLongitude(detailResultSet.getString("longitude"));
                    temperatureChangeCityRecordDTO.setLatitude(detailResultSet.getString("latitude"));
                    if (detailResultSet.getObject("start_year_temperature") != null) {
                        temperatureChangeCityRecordDTO.setStartYearTemperature(detailResultSet.getDouble("start_year_temperature"));
                    }
                    if (detailResultSet.getObject("end_year_temperature") != null) {
                        temperatureChangeCityRecordDTO.setEndYearTemperature(detailResultSet.getDouble("end_year_temperature"));
                    }
                    if (detailResultSet.getObject("raw_value_change_temperature") != null) {
                        temperatureChangeCityRecordDTO.setRawValueChangeTemperature(detailResultSet.getDouble("raw_value_change_temperature"));
                    }
                    if (detailResultSet.getObject("percentage_change_temperature") != null) {
                        temperatureChangeCityRecordDTO.setPercentageChangeTemperature(detailResultSet.getDouble("percentage_change_temperature"));
                    }
                }
                temperatureChangeCityRecordDTOs.add(temperatureChangeCityRecordDTO);
                detailResultSet.close();
                detailStatement.close();
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findRawAndPercentageChangeRecordsByCountry method in CityRepository class");
            e.printStackTrace();
        }

        return temperatureChangeCityRecordDTOs;
    }



    public List<CityDataWithinPeriodDTO> findAverageValuesOfTemperatureCityRecords(
            List<Integer> cityIds,
            List<Integer> startYears,
            int yearLengths
    ) {
        List<CityDataWithinPeriodDTO> cityDataWithinPeriodDTOS = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            for(int startYear: startYears){
                    String query = """
                            SELECT country.name AS country_name,
                                   city.id, city.name,
                                   AVG(city_record.average_temperature)
                                       AS average_value_of_temperature,
                                   COUNT(city_record.average_temperature)
                                       AS available_year_count
                            FROM
                                city
                            LEFT JOIN
                                city_record ON city.id = city_record.city_id
                                    AND city_record.year BETWEEN {startYear} AND {endYear}
                            INNER JOIN
                                country ON city.country_id = country.id
                            WHERE
                                city.id IN ({countryIds})
                            GROUP BY
                                city.id
                            ORDER BY
                                Case When AVG(city_record.average_temperature) IS NULL THEN 1 ELSE 0 END,
                                average_value_of_temperature ASC
                            """.replace("{startYear}", String.valueOf(startYear))
                                .replace("{endYear}", String.valueOf(startYear + yearLengths - 1))
                                .replace("{countryIds}", String.join((CharSequence) ",", cityIds.stream().map(String::valueOf).toArray(String[]::new)));
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);
                    ResultSet results = statement.executeQuery(query);
                    List<AverageValueOfTemperatureCityRecordDTO> averageValueOfTemperatureCityRecordDTOs
                        = new ArrayList<>();
                    CityDataWithinPeriodDTO cityDataWithinPeriodDTO = new CityDataWithinPeriodDTO();
                    cityDataWithinPeriodDTO.setStartYear(startYear);
                    cityDataWithinPeriodDTO.setYearLength(yearLengths);
                    int number = 1;
                    while(results.next()){
                        AverageValueOfTemperatureCityRecordDTO averageValueOfTemperatureCityRecordDTO
                            = new AverageValueOfTemperatureCityRecordDTO();
                        averageValueOfTemperatureCityRecordDTO.setId(results.getInt("id"));
                        averageValueOfTemperatureCityRecordDTO.setNumber(number++);
                        averageValueOfTemperatureCityRecordDTO.setName(results.getString("name"));
                        averageValueOfTemperatureCityRecordDTO.setCountryName(results.getString("country_name"));
                        averageValueOfTemperatureCityRecordDTO.setStartYear(startYear);
                        averageValueOfTemperatureCityRecordDTO.setEndYear(startYear + yearLengths - 1);
                        if(results.getObject("average_value_of_temperature") != null){
                            averageValueOfTemperatureCityRecordDTO.setAverageValueOfTemperature
                                    (results.getDouble("average_value_of_temperature"));
                        }
                        if(results.getObject("available_year_count") != null){
                            averageValueOfTemperatureCityRecordDTO.setAvailableYearCount(results.getInt("available_year_count"));
                        }
                        averageValueOfTemperatureCityRecordDTOs.add(averageValueOfTemperatureCityRecordDTO);

                    }
                cityDataWithinPeriodDTO.setAverageValueOfTemperatureCityRecordDTOs
                        (averageValueOfTemperatureCityRecordDTOs);
                cityDataWithinPeriodDTOS.add(cityDataWithinPeriodDTO);
            }
        }
        catch (Exception e) {
            System.err.println("Error in findAverageValuesOfTemperatureCityRecords method in CityRepository class");
            e.printStackTrace();
        }
        return cityDataWithinPeriodDTOS;
    }

    public int findAvailableYearCountByCityId(int cityId, int startYear, int endYear){
        int availableYearCount = 0;
        try(Connection connection = DriverManager.getConnection(DATABASE)){
            String query = """
                    SELECT
                        COUNT(DISTINCT year) AS available_year_count
                    FROM
                        city_record
                    WHERE
                        city_id = {cityId}
                        AND year BETWEEN {startYear} AND {endYear}
                    """.replace("{cityId}", String.valueOf(cityId))
                        .replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            if(results.next()){
                availableYearCount = results.getInt("available_year_count");
            }
        }
        catch (Exception e) {
            System.err.println("Error in findAvailableYearCountByCityId method in CityRepository class");
            e.printStackTrace();
        }
        return availableYearCount;
    }

    public List<SimilarPeriodCityRecordDTO> findSimilarPeriodsByCityId(
            int cityId, int startYear, int yearLength, int limit) {
        List<SimilarPeriodCityRecordDTO> similarPeriodCityRecordDTOs = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE)){
            String query = """
                    SELECT
                        city.name AS name,
                        city.id AS id,
                        temp_data.start_year,
                        temp_data.average_temperature,
                        temp_data.temperature_difference
                    FROM
                        city
                    JOIN
                    (
                        WITH temperature_data AS (
                            SELECT
                                city_id,
                                year AS start_year,
                                ROUND(AVG(average_temperature) OVER (
                                    PARTITION BY city_id
                                    ORDER BY year
                                    RANGE BETWEEN CURRENT ROW AND {yearLength} - 1 FOLLOWING
                                ), 5) AS average_temperature
                            FROM
                                city_record
                            WHERE
                                year <= {maxYear} - {yearLength} +1
                        ), reference_period AS (
                            SELECT
                                AVG(average_temperature) AS reference_temperature
                            FROM
                                city_record
                            WHERE
                                city_id = {cityId} AND year BETWEEN {startYear} AND {endYear}
                            GROUP BY
                                city_id
                        )
                        SELECT
                            city_id,
                            start_year,
                            average_temperature,
                            ABS(average_temperature - (SELECT reference_temperature FROM reference_period))
                                AS temperature_difference
                        FROM
                            temperature_data
                    ) AS temp_data
                    ON
                        city.id = temp_data.city_id
                    ORDER BY
                        temp_data.temperature_difference
                    LIMIT {limit}
                    """.replace("{yearLength}", String.valueOf(yearLength))
                        .replace("{cityId}", String.valueOf(cityId))
                        .replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(startYear + yearLength - 1))
                        .replace("{limit}", String.valueOf(limit))
                    .replace("{maxYear}", String.valueOf(findYearRangeCityRecord().getMaxYear())
                    );
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            int number = 1;
            while(results.next()){
                SimilarPeriodCityRecordDTO similarPeriodCityRecordDTO = new SimilarPeriodCityRecordDTO();

                similarPeriodCityRecordDTO.setId(results.getInt("id"));

                similarPeriodCityRecordDTO.setName(results.getString("name"));
                similarPeriodCityRecordDTO.setStartYear(results.getInt("start_year"));
                similarPeriodCityRecordDTO.setEndYear(results.getInt("start_year") + yearLength - 1);
                similarPeriodCityRecordDTO.setAverageValueOfTemperature(results.getDouble("average_temperature"));
                similarPeriodCityRecordDTO.setTemperatureDifference(results.getDouble("temperature_difference"));
                similarPeriodCityRecordDTO.setAvailableYearCount(findAvailableYearCountByCityId(similarPeriodCityRecordDTO.getId(),
                        similarPeriodCityRecordDTO.getStartYear(), similarPeriodCityRecordDTO.getEndYear()));
                CityDTO cityDTO = findCityById(similarPeriodCityRecordDTO.getId());
                similarPeriodCityRecordDTO.setLongitude(cityDTO.getLongitude());
                similarPeriodCityRecordDTO.setLatitude(cityDTO.getLatitude());
                similarPeriodCityRecordDTO.setCountryName(cityDTO.getCountryName());
                if(results.getInt("id") == cityId && results.getInt("start_year") == startYear) {
                    similarPeriodCityRecordDTO.setNumber(0);
                    similarPeriodCityRecordDTOs.add(0, similarPeriodCityRecordDTO);
                    continue;
                };
                similarPeriodCityRecordDTO.setNumber(number++);
                similarPeriodCityRecordDTOs.add(similarPeriodCityRecordDTO);


            }
        }
        catch (Exception e) {
            System.err.println("Error in findSimilarPeriodsByCityId method in CityRepository class");
            e.printStackTrace();
        }
        return similarPeriodCityRecordDTOs;
    }


}
