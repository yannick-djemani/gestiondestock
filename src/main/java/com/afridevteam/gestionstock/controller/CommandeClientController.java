package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.CommandeClientApi;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.service.CommandeClientService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {
    private final CommandeClientService commandeClientService;

    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto findById(Long id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }


    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Long id) {
        commandeClientService.delete(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        return commandeClientService.updateEtatCommande(idCommande, etatCommande);
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        return commandeClientService.updateQuantiteCommande(idCommande, quantite, idLigneCommande);
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        return commandeClientService.updateClient(idCommande, idClient);
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        return commandeClientService.updateArticle(idCommande, idNewArticle, idLigneCommande);
    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return commandeClientService.deleteArticle(idCommande, idLigneCommande);
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByIdLigneCommandeClient(Long id) {
        return commandeClientService.findAllLigneCommandeClientByIdLigneCommandeClient(id);
    }
}
