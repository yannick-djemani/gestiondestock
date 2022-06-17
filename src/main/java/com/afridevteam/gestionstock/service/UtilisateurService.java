package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    Optional<UtilisateurDto> save(UtilisateurDto dto);

    Optional<UtilisateurDto> findById(Long id);

    Optional<UtilisateurDto> findByEmail(String email);

    List<UtilisateurDto> findAll();

    Optional<UtilisateurDto> changePassword();

    Optional<UtilisateurDto> currentUser();

    void delete(Long id);
}
