package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    
    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Long id);

    EntrepriseDto findByNom(String name);

    List<EntrepriseDto> findAll();

    void delete(Long id);

}
