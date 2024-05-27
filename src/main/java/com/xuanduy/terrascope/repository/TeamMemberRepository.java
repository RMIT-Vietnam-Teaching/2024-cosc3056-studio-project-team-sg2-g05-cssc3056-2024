package com.xuanduy.terrascope.repository;

import com.xuanduy.terrascope.model.TeamMember;
import org.springframework.stereotype.Repository;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamMemberRepository {
    public static final String DATABASE = "jdbc:sqlite:database/web.db";


    public List<TeamMember> findAllTeamMembers() {
        List<TeamMember> teamMembers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM team_member")) {
            while (resultSet.next()) {
                TeamMember teamMember = new TeamMember();
                teamMember.setId(resultSet.getInt("id"));
                teamMember.setName(resultSet.getString("name"));
                teamMember.setAge(resultSet.getInt("age"));
                teamMember.setGmail(resultSet.getString("email"));
                teamMember.setQuote(resultSet.getString("quote"));
                teamMembers.add(teamMember);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamMembers;
     }
}
