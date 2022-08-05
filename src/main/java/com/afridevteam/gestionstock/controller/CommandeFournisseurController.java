package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.CommandeFournisseurApi;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.service.CommandeFournisseurService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {
    private final CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        return null;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournisseurDto findByNom(String name) {
        return null;
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByIdLigneCommandeFournisseur(Long id) {
        return null;
    }
}
