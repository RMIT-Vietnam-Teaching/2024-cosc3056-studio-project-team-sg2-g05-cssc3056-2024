package com.xuanduy.terrascope.controller;

import com.xuanduy.terrascope.dto.persona.respond.PersonasDTO;
import com.xuanduy.terrascope.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;
    @GetMapping(value = "")
    public List<PersonasDTO> getAllPersonas() {
        return personaService.getAllPersonas() ;
    }
}
