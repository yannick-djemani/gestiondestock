package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.EntrepriseApi;
import com.afridevteam.gestionstock.dto.EntrepriseDto;
import com.afridevteam.gestionstock.service.EntrepriseService;

import java.util.List;

public class EntrepriseController implements EntrepriseApi {
    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {this.entrepriseService = entrepriseService;}

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Long id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto findByNom(String name) {
        return entrepriseService.findByNom(name);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Long id) {
        entrepriseService.delete(id);

    }
}
