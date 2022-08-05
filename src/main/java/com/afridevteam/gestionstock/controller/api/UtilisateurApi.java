package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.ChangeMotDePasseDto;
import com.afridevteam.gestionstock.dto.UtilisateurDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.UTILISATEUR_ENDPOINT;

public interface UtilisateurApi {
    UtilisateurDto findById(Long id);

    @PostMapping(UTILISATEUR_ENDPOINT)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(UTILISATEUR_ENDPOINT)
    List<UtilisateurDto> findAll();


    void delete(Long id);

    UtilisateurDto findByEmail(String email);

    UtilisateurDto changePassword(ChangeMotDePasseDto dto);

    UtilisateurDto currentUser();

}
