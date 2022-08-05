package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.*;

public interface EntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(ENTREPRISE_ENPOINT_BY_ID)
    EntrepriseDto findById(@PathVariable("id") Long id);

    @GetMapping(ENTREPRISE_ENPOINT_BY_NAME)
    EntrepriseDto findByNom(@PathVariable("name") String name);

    @GetMapping(ENTREPRISE_ENPOINT)
    List<EntrepriseDto> findAll();

    @DeleteMapping(ENTREPRISE_ENPOINT_BY_ID)
    void delete(@PathVariable("id") Long id);
}
