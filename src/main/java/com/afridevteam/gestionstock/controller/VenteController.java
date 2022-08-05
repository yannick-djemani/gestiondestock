package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.VenteApi;
import com.afridevteam.gestionstock.dto.VenteDto;
import com.afridevteam.gestionstock.service.VenteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {
    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }


    @Override
    public VenteDto findById(Long id) {
        return venteService.findById(id);
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return venteService.save(dto);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Long id) {
        venteService.delete(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return venteService.findByCode(code);
    }
}
