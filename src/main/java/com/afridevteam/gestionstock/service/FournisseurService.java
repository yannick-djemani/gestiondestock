package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.ClientDto;
import com.afridevteam.gestionstock.dto.FournisseurDto;

import java.util.List;
import java.util.Optional;

public interface FournisseurService {
    Optional<FournisseurDto> save(ClientDto dto);

    Optional<FournisseurDto> findById(Long id);

    Optional<FournisseurDto> findByNom(String name);

    List<FournisseurDto> findAll();

    //void delete(Long id);
}
