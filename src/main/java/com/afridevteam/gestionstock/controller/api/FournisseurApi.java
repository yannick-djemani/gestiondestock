package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurApi {
    FournisseurDto findById(Long id);


    FournisseurDto save(FournisseurDto dto);


    List<FournisseurDto> findAll();


    void delete(Long id);
}
