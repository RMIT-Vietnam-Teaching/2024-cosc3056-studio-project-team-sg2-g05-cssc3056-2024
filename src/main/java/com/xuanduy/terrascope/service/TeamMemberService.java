package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.model.TeamMember;
import com.xuanduy.terrascope.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {
    @Autowired
    public TeamMemberRepository teamMemberRepository;

    public List<TeamMember> getAllTeamMembers(){
        return teamMemberRepository.findAllTeamMembers();
    }
}
