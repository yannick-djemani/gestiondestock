package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.UtilisateurApi;
import com.afridevteam.gestionstock.dto.ChangeMotDePasseDto;
import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.service.UtilisateurService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }


    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurService.findByEmail(email);
    }

    @Override
    public UtilisateurDto changePassword(ChangeMotDePasseDto dto) {
        return utilisateurService.changePassword(dto);
    }

    @Override
    public UtilisateurDto currentUser() {
        return utilisateurService.currentUser();
    }
}
