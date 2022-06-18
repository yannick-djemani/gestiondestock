package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.UtilisateurDto;

import java.util.Optional;

public interface UtilisateurService extends EntityService<UtilisateurDto> {


    Optional<UtilisateurDto> findByEmail(String email);
    
    Optional<UtilisateurDto> changePassword();

    Optional<UtilisateurDto> currentUser();

}
