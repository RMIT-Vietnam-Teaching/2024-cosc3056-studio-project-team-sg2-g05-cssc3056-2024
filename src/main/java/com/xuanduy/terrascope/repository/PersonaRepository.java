package com.xuanduy.terrascope.repository;

import com.xuanduy.terrascope.dto.persona.respond.PersonasDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonaRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";
    public List<PersonasDTO> findAllPersonas() {
        List<PersonasDTO> personasDTOs = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE)){  ;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("""
                    SELECT * FROM persona
                    """);
            while (resultSet.next()) {
                List<String> painPoints = new ArrayList<>();
                List<String> needsAndGoals = new ArrayList<>();
                List<String> background = new ArrayList<>();
                List<String> skillAndExperiences = new ArrayList<>();
                PersonasDTO personasDTO = new PersonasDTO();
                personasDTO.setId(resultSet.getInt("id"));
                personasDTO.setName(resultSet.getString("name"));
                personasDTO.setAge(resultSet.getString("age"));
                personasDTO.setLocation(resultSet.getString("location"));
                personasDTO.setGender(resultSet.getString("gender"));
                Statement statementPaintPoint = connection.createStatement();
                ResultSet resultSetPaintPoint = statementPaintPoint.executeQuery("""
                        SELECT content FROM persona
                            LEFT JOIN has on persona.id = persona_id
                            LEFT JOIN attribute on attribute.id = attribute_id
                            WHERE persona.id = {id} AND attribute.type = "Pain Points"
                        """.replace("{id}", String.valueOf(personasDTO.getId())));
                while (resultSetPaintPoint.next()) {
                    painPoints.add(resultSetPaintPoint.getString("content"));
                }
                personasDTO.setPainPoints(painPoints);
                Statement statementNeedsAndGoals = connection.createStatement();
                ResultSet resultSetNeedsAndGoals = statementNeedsAndGoals.executeQuery("""
                        SELECT content FROM persona
                            LEFT JOIN has on persona.id = persona_id
                            LEFT JOIN attribute on attribute.id = attribute_id
                            WHERE persona.id = {id} AND attribute.type = "Needs and Goals"
                        """.replace("{id}", String.valueOf(personasDTO.getId())));
                while (resultSetNeedsAndGoals.next()) {
                    needsAndGoals.add(resultSetNeedsAndGoals.getString("content"));

                }
                personasDTO.setNeedsAndGoals(needsAndGoals);
                Statement statementBackground = connection.createStatement();
                ResultSet resultSetBackground = statementBackground.executeQuery("""
                        SELECT content FROM persona
                            LEFT JOIN has on persona.id = persona_id
                            LEFT JOIN attribute on attribute.id = attribute_id
                            WHERE persona.id = {id} AND attribute.type = "Background"
                        """.replace("{id}", String.valueOf(personasDTO.getId())));
                while (resultSetBackground.next()) {
                    background.add(resultSetBackground.getString("content"));

                }
                personasDTO.setBackground(background);
                Statement statementSkillAndExperiences = connection.createStatement();
                ResultSet resultSetSkillAndExperiences = statementSkillAndExperiences.executeQuery("""
                        SELECT content FROM persona
                            LEFT JOIN has on persona.id = persona_id
                            LEFT JOIN attribute on attribute.id = attribute_id
                            WHERE persona.id = {id} AND attribute.type = "Skill and Experiences"
                        """.replace("{id}", String.valueOf(personasDTO.getId())));
                while (resultSetSkillAndExperiences.next()) {
                    skillAndExperiences.add(resultSetSkillAndExperiences.getString("content"));

                }
                personasDTO.setSkillAndExperiences(skillAndExperiences);
                personasDTOs.add(personasDTO);
            }
        } catch (SQLException e) {
            System.err.println("Error found in PersonaRepository.findAllPersonas() method");
            e.printStackTrace();
        }
        return personasDTOs;
    }
}
