package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.CategorieDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.CATEGORIE_ENPOINT;
import static com.afridevteam.gestionstock.utils.Constants.CATEGORIE_ENPOINT_BY_CODE;
import static com.afridevteam.gestionstock.utils.Constants.CATEGORIE_ENPOINT_BY_ID;

public interface CategorieApi {
    @PostMapping(value = CATEGORIE_ENPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto save(@RequestBody CategorieDto dto);

    @GetMapping(CATEGORIE_ENPOINT_BY_ID)
    CategorieDto findById(@PathVariable("id") Long id);

    @GetMapping(CATEGORIE_ENPOINT_BY_CODE)
    CategorieDto findByCode(@PathVariable("code") String code);

    @GetMapping(CATEGORIE_ENPOINT)
    List<CategorieDto> findAll();

    @DeleteMapping(CATEGORIE_ENPOINT_BY_ID)
    void delete(@PathVariable("id") Long id);
}
