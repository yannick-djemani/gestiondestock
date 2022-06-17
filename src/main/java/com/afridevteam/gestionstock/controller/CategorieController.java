package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.CategorieApi;
import com.afridevteam.gestionstock.dto.CategorieDto;
import com.afridevteam.gestionstock.service.CategorieService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {this.categorieService = categorieService;}

    @Override
    public CategorieDto save(CategorieDto dto) {
        return categorieService.save(dto);
    }

    @Override
    public CategorieDto findById(Long id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCode(String code) {
        return categorieService.findByCode(code);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Long id) {
        categorieService.delete(id);
    }
}
