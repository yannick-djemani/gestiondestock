package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.ChangeMotDePasseDto;
import com.afridevteam.gestionstock.dto.UtilisateurDto;

public interface UtilisateurService extends EntityService<UtilisateurDto> {


    UtilisateurDto findByEmail(String email);

    UtilisateurDto changePassword(ChangeMotDePasseDto dto);

    UtilisateurDto currentUser();

}
