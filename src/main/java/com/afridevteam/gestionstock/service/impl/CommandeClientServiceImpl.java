package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.service.CommandeClientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("CommandeClientService")
public class CommandeClientServiceImpl implements CommandeClientService {
    @Override
    public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        return null;
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return null;
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByIdLigneCommandeClient(Long id) {
        return null;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return null;
    }

    @Override
    public CommandeClientDto findById(Long id) {
        return null;
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
