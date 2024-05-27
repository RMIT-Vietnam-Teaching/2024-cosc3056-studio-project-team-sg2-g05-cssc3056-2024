package com.xuanduy.terrascope.service;

import com.xuanduy.terrascope.dto.persona.respond.PersonasDTO;
import com.xuanduy.terrascope.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    public PersonaRepository personaRepository;

    public List<PersonasDTO> getAllPersonas() {
        return personaRepository.findAllPersonas();
    }
}
