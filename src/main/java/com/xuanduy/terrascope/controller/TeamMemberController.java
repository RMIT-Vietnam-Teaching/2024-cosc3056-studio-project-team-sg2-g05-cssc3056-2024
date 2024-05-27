package com.xuanduy.terrascope.controller;

import com.xuanduy.terrascope.model.TeamMember;
import com.xuanduy.terrascope.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/team-members")
public class TeamMemberController {
    @Autowired
    public TeamMemberService teamMemberService;
    @GetMapping (value ="")
    public List<TeamMember> getAllTeamMembers(){
        return teamMemberService.getAllTeamMembers();
    }
}
