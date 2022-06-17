package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.ENTREPRISE_ENPOINT;
import static com.afridevteam.gestionstock.utils.Constants.ENTREPRISE_ENPOINT_BY_ID;
import static com.afridevteam.gestionstock.utils.Constants.ENTREPRISE_ENPOINT_BY_NAME;

public interface EntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(EntrepriseDto dto);

    @GetMapping(ENTREPRISE_ENPOINT_BY_ID)
    EntrepriseDto findById(Long id);

    @GetMapping(ENTREPRISE_ENPOINT_BY_NAME)
    EntrepriseDto findByNom(String name);

    @GetMapping(ENTREPRISE_ENPOINT)
    List<EntrepriseDto> findAll();

    @DeleteMapping(ENTREPRISE_ENPOINT_BY_ID)
    void delete(Long id);
}
