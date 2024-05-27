package com.xuanduy.terrascope.repository;
import com.xuanduy.terrascope.dto.state.respond.*;
import com.xuanduy.terrascope.dto.util.respond.YearRangeDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class StateRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";

    public static List<StateDTO> findAllStates() {
        List<StateDTO> stateDTOs = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DATABASE);
            String query = """
                    SELECT state.id, state.name, country.name as country_name
                        FROM
                            state
                        INNER JOIN
                            country
                        ON
                            state.country_id = country.id
                    """;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                StateDTO stateDTO = new StateDTO();
                stateDTO.setId(resultSet.getLong("id"));
                stateDTO.setName(resultSet.getString("name"));
                stateDTO.setCountryName(resultSet.getString("country_name"));
                stateDTOs.add(stateDTO);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stateDTOs;
    }

    public static YearRangeDTO findYearRangeStateRecord() {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try {
            Connection connection = DriverManager.getConnection(DATABASE);
            String query = """
                    SELECT
                        MIN(year) as min_year,
                        MAX(year) as max_year
                    FROM
                        state_record
                    """;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                yearRangeDTO.setMinYear(resultSet.getInt("min_year"));
                yearRangeDTO.setMaxYear(resultSet.getInt("max_year"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yearRangeDTO;
    }
    public YearRangeDTO findYearRangeByStateId(int StateId) {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        MIN(year) as min_year,
                        MAX(year) as max_year
                    FROM
                        state_record
                    WHERE
                        state_id = {stateId}
                    """.replace("{stateId}", String.valueOf(StateId));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                yearRangeDTO.setMinYear(resultSet.getInt("min_year"));
                yearRangeDTO.setMaxYear(resultSet.getInt("max_year"));
            }
            connection.close();
        } catch (Exception e) {
            System.err.println("Error in findYearRangeByStateId method in StateRepository class");
            e.printStackTrace();
        }
        return yearRangeDTO;
    }

    public List<Integer> findUnavailableYearsByStateId(
            int StateId,
            int startYear,
            int endYear
    ) {
        List<Integer> unavailableYears = new ArrayList<>();
        YearRangeDTO yearRangeDTO = findYearRangeByStateId(StateId);

        if(endYear < yearRangeDTO.getMinYear()) return unavailableYears;
        if(startYear > yearRangeDTO.getMaxYear()) return unavailableYears;
        startYear = Math.max(startYear, yearRangeDTO.getMinYear());
        endYear = Math.min(endYear, yearRangeDTO.getMaxYear());

        try(Connection connection = DriverManager.getConnection(DATABASE)) {
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
                        FROM state_record
                        WHERE state_id = {stateId} AND year BETWEEN {startYear} AND {endYear}
                    ) AS years ON all_years.year = years.year
                    WHERE years.year IS NULL;
                    """.replace("{stateId}", String.valueOf(StateId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                unavailableYears.add(resultSet.getInt("year"));
            }
            connection.close();
        } catch (Exception e) {
            System.err.println("Error in findUnavailableYearsByStateId method in StateRepository class");
            e.printStackTrace();
        }
        return unavailableYears;
    }

    public List<TemperatureChangeStateRecordDTO> findTemperatureChangeStateRecordsByCountryId(
            int startYear, int endYear, int countryId, String typeOfTemperature) {
        List<TemperatureChangeStateRecordDTO> temperatureChangeStateRecordDTOs = new ArrayList<>();
        String temperatureColumn = switch (typeOfTemperature) {
            case "MaxTemperature" -> "maximum_temperature";
            case "MinTemperature" -> "minimum_temperature";
            case "AverageTemperature" -> "average_temperature";
            default -> throw new IllegalStateException("Unexpected value: " + typeOfTemperature);
        };

        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String initialQuery = """
        SELECT
            state_id AS id,
            AVG(percentage_change) AS average_percentage_change,
            COUNT(percentage_change) AS available_year_count
        FROM (
            SELECT
                year,
                state_id,
                average_temperature,
                ({temperatureColumn} - LAG({temperatureColumn}) OVER (PARTITION BY state_id ORDER BY year)) /
                    ABS(LAG({temperatureColumn}) OVER (PARTITION BY state_id ORDER BY year))
                        AS percentage_change
            FROM
                state_record
            WHERE
                state_id IN (
                    SELECT
                        id
                    FROM
                        state
                    WHERE
                        country_id = {countryId}
                    )
                AND
                    year BETWEEN {startYear} AND {endYear}
            )
        GROUP BY
            state_id
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
                TemperatureChangeStateRecordDTO temperatureChangeStateRecordDTO = new TemperatureChangeStateRecordDTO();
                temperatureChangeStateRecordDTO.setId(resultSet.getInt("id"));
                if (resultSet.getObject("average_percentage_change") != null) {
                    temperatureChangeStateRecordDTO.setAveragePercentageChangeTemperature(
                            resultSet.getDouble("average_percentage_change"));
                }
                temperatureChangeStateRecordDTO.setNumber(number++);
                temperatureChangeStateRecordDTO.setAvailableYearCount(
                        resultSet.getInt("available_year_count"));
                String detailQuery = """
                        SELECT
                            state.name,
                            sr1.{temperatureColumn} AS start_year_temperature,
                            sr2.{temperatureColumn} AS end_year_temperature,
                            sr2.{temperatureColumn} - sr1.{temperatureColumn}
                                AS raw_value_change_temperature,
                            (sr2.{temperatureColumn} - sr1.{temperatureColumn}) / ABS(sr1.{temperatureColumn}) 
                                AS percentage_change_temperature
                        FROM
                            state
                        LEFT JOIN
                            state_record AS sr1 
                                ON sr1.state_id = state.id AND sr1.year = {startYear}
                        LEFT JOIN
                            state_record AS sr2 
                                ON sr2.state_id = state.id AND sr2.year = {endYear}
                        WHERE
                            state.id = {stateId}
                    """.replace("{temperatureColumn}", temperatureColumn)
                        .replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(endYear))
                        .replace("{stateId}", String.valueOf(temperatureChangeStateRecordDTO.getId()));

                Statement detailStatement = connection.createStatement();
                ResultSet detailResultSet = detailStatement.executeQuery(detailQuery);
                if (detailResultSet.next()) {
                    temperatureChangeStateRecordDTO.setName(detailResultSet.getString("name"));
                    temperatureChangeStateRecordDTO.setStartYear(startYear);
                    temperatureChangeStateRecordDTO.setEndYear(endYear);

                    if (detailResultSet.getObject("start_year_temperature") != null) {
                        temperatureChangeStateRecordDTO.setStartYearTemperature(detailResultSet.getDouble("start_year_temperature"));
                    }
                    if (detailResultSet.getObject("end_year_temperature") != null) {
                        temperatureChangeStateRecordDTO.setEndYearTemperature(detailResultSet.getDouble("end_year_temperature"));
                    }
                    if (detailResultSet.getObject("raw_value_change_temperature") != null) {
                        temperatureChangeStateRecordDTO.setRawValueChangeTemperature(detailResultSet.getDouble("raw_value_change_temperature"));
                    }
                    if (detailResultSet.getObject("percentage_change_temperature") != null) {
                        temperatureChangeStateRecordDTO.setPercentageChangeTemperature(detailResultSet.getDouble("percentage_change_temperature"));
                    }
                }

                temperatureChangeStateRecordDTOs.add(temperatureChangeStateRecordDTO);

                detailResultSet.close();
                detailStatement.close();
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.err.println("Error in findTemperatureChangeStateRecordsByCountry method in StateRepository class");
            e.printStackTrace();
        }

        return temperatureChangeStateRecordDTOs;
    }





    public YearRangeDTO findStateYearRangeById(int stateId) {
        YearRangeDTO yearRangeDTO = new YearRangeDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        MIN(year) as min_year,
                        MAX(year) as max_year
                    FROM
                        state_record
                    WHERE
                        state_id = {stateId}
                    """.replace("{stateId}", String.valueOf(stateId));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                yearRangeDTO.setMinYear(resultSet.getInt("min_year"));
                yearRangeDTO.setMaxYear(resultSet.getInt("max_year"));
            }
        } catch (Exception e) {
            System.err.println("Error in findStateYearRangeById method in StateRepository class");
            e.printStackTrace();}
        return yearRangeDTO;
    }

    public List<SimilarPeriodStateRecordDTO> findSimilarPeriodsByStateId(
            int stateId, int startYear, int yearLength, int limit) {
        List<SimilarPeriodStateRecordDTO> similarPeriodStateRecordDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        state.name AS name,
                        state.id AS id,
                        temp_data.start_year,
                        temp_data.average_temperature,
                        temp_data.temperature_difference
                    FROM
                        state
                    JOIN
                    (
                        WITH temperature_data AS (
                            SELECT
                                state_id,
                                year AS start_year,
                                ROUND(AVG(average_temperature) OVER (
                                    PARTITION BY state_id
                                    ORDER BY year
                                    RANGE BETWEEN CURRENT ROW AND {yearLength} - 1 FOLLOWING
                                ), 5) AS average_temperature
                            FROM
                                state_record
                            WHERE
                                year <= {maxYear} - {yearLength} +1
                        ), reference_period AS (
                            SELECT
                                AVG(average_temperature) AS reference_temperature
                            FROM
                                state_record
                            WHERE
                                state_id = {stateId} AND year BETWEEN {startYear} AND {endYear}
                            GROUP BY
                                state_id
                        )
                        SELECT
                            state_id,
                            start_year,
                            average_temperature,
                            ABS(average_temperature - (SELECT reference_temperature FROM reference_period))
                                AS temperature_difference
                        FROM
                            temperature_data
                    ) AS temp_data
                    ON
                        state.id = temp_data.state_id
                    ORDER BY
                        temp_data.temperature_difference
                    LIMIT {limit}
                    """.replace("{yearLength}", String.valueOf(yearLength))
                    .replace("{stateId}", String.valueOf(stateId))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(startYear + yearLength - 1))
                    .replace("{limit}", String.valueOf(limit))
                    .replace("{maxYear}", String.valueOf(findYearRangeStateRecord().getMaxYear())
                    );
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);
            int number = 1;
            while (results.next()) {
                SimilarPeriodStateRecordDTO similarPeriodStateRecordDTO = new SimilarPeriodStateRecordDTO();

                similarPeriodStateRecordDTO.setId(results.getInt("id"));

                similarPeriodStateRecordDTO.setName(results.getString("name"));
                similarPeriodStateRecordDTO.setStartYear(results.getInt("start_year"));
                similarPeriodStateRecordDTO.setEndYear(results.getInt("start_year") + yearLength - 1);
                similarPeriodStateRecordDTO.setAverageValueOfTemperature(results.getDouble("average_temperature"));
                similarPeriodStateRecordDTO.setTemperatureDifference(results.getDouble("temperature_difference"));
                similarPeriodStateRecordDTO.setAvailableYearCount(findAvailableYearCountByStateId(similarPeriodStateRecordDTO.getId(),
                        similarPeriodStateRecordDTO.getStartYear(), similarPeriodStateRecordDTO.getEndYear()));
                StateDTO stateDTO = findStateById(similarPeriodStateRecordDTO.getId());
                similarPeriodStateRecordDTO.setCountryName(stateDTO.getCountryName());
                if (results.getInt("id") == stateId && results.getInt("start_year") == startYear) {
                    similarPeriodStateRecordDTO.setNumber(0);
                    similarPeriodStateRecordDTOs.add(0, similarPeriodStateRecordDTO);
                    continue;
                }
                ;
                similarPeriodStateRecordDTO.setNumber(number++);
                similarPeriodStateRecordDTOs.add(similarPeriodStateRecordDTO);
            }
        } catch (Exception e) {
            System.err.println("Error in findSimilarPeriodsByStateId method in StateRepository class");
            e.printStackTrace();
        }
        return similarPeriodStateRecordDTOs;
    }

    private StateDTO findStateById(int id) {
        StateDTO stateDTO = new StateDTO();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        state.id, state.name, country.name as country_name
                    FROM
                        state
                    INNER JOIN
                        country
                    ON
                        state.country_id = country.id
                    WHERE
                        state.id = {id}
                    """.replace("{id}", String.valueOf(id));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                stateDTO.setId(resultSet.getLong("id"));
                stateDTO.setName(resultSet.getString("name"));
                stateDTO.setCountryName(resultSet.getString("country_name"));
            }
        } catch (Exception e) {
            System.err.println("Error in findStateById method in StateRepository class");
            e.printStackTrace();
        }
        return stateDTO;
    }

    private int findAvailableYearCountByStateId(int id, int startYear, int endYear) {
        int availableYearCount = 0;
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            String query = """
                    SELECT
                        COUNT(*)
                    FROM
                        state_record
                    WHERE
                        state_id = {id}
                        AND
                        year BETWEEN {startYear} AND {endYear}
                    """.replace("{id}", String.valueOf(id))
                    .replace("{startYear}", String.valueOf(startYear))
                    .replace("{endYear}", String.valueOf(endYear));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                availableYearCount = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Error in findAvailableYearCountByStateId method in StateRepository class");
            e.printStackTrace();
        }
        return availableYearCount;
    }



    public List<StateDataWithinPeriodDTO> findAverageValuesOfTemperatureStateRecords(
            List<Integer> stateIds, List<Integer> startYears, int yearLength) {
        List<StateDataWithinPeriodDTO> stateDataWithinPeriodDTOS = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE)) {
            for (int startYear : startYears) {
                String query = """
                        SELECT
                            country.name AS country_name,
                            state.id,
                            state.name,
                            AVG(state_record.average_temperature) AS average_value_of_temperature,
                            COUNT(state_record.average_temperature) AS available_year_count
                        FROM
                            state
                        LEFT JOIN
                            state_record ON state.id = state_record.state_id
                                AND state_record.year BETWEEN {startYear} AND {endYear}
                        INNER JOIN
                            country ON state.country_id = country.id
                        WHERE
                            state.id IN ({stateIds})
                        GROUP BY
                            state.id
                        ORDER BY
                            CASE WHEN AVG(state_record.average_temperature) IS NULL THEN 1 ELSE 0 END,
                            average_value_of_temperature ASC
                        """.replace("{startYear}", String.valueOf(startYear))
                        .replace("{endYear}", String.valueOf(startYear + yearLength - 1))
                        .replace("{stateIds}", String.join(",", stateIds.stream().map(String::valueOf).toArray(String[]::new)));
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet results = statement.executeQuery(query);
                List<AverageValueOfTemperatureStateRecordDTO> averageValueOfTemperatureStateRecordDTOs = new ArrayList<>();
                StateDataWithinPeriodDTO stateDataWithinPeriodDTO = new StateDataWithinPeriodDTO();
                stateDataWithinPeriodDTO.setStartYear(startYear);
                stateDataWithinPeriodDTO.setYearLength(yearLength);
                int number = 1;
                while (results.next()) {
                    AverageValueOfTemperatureStateRecordDTO averageValueOfTemperatureStateRecordDTO = new AverageValueOfTemperatureStateRecordDTO();
                    averageValueOfTemperatureStateRecordDTO.setId(results.getInt("id"));
                    averageValueOfTemperatureStateRecordDTO.setNumber(number++);
                    averageValueOfTemperatureStateRecordDTO.setName(results.getString("name"));
                    averageValueOfTemperatureStateRecordDTO.setCountryName(results.getString("country_name"));
                    averageValueOfTemperatureStateRecordDTO.setStartYear(startYear);
                    averageValueOfTemperatureStateRecordDTO.setEndYear(startYear + yearLength - 1);
                    if (results.getObject("average_value_of_temperature") != null) {
                        averageValueOfTemperatureStateRecordDTO.setAverageValueOfTemperature(results.getDouble("average_value_of_temperature"));
                    }
                    if (results.getObject("available_year_count") != null) {
                        averageValueOfTemperatureStateRecordDTO.setAvailableYearCount(results.getInt("available_year_count"));
                    }
                    averageValueOfTemperatureStateRecordDTOs.add(averageValueOfTemperatureStateRecordDTO);
                }
                stateDataWithinPeriodDTO.setAverageValueOfTemperatureStateRecordDTOs(averageValueOfTemperatureStateRecordDTOs);
                stateDataWithinPeriodDTOS.add(stateDataWithinPeriodDTO);
            }
        } catch (Exception e) {
            System.err.println("Error in findAverageValuesOfTemperatureStateRecords method in StateRepository class");
            e.printStackTrace();
        }
        return stateDataWithinPeriodDTOS;
    }
}