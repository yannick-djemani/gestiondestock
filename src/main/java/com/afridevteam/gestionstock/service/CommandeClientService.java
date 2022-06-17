package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService extends EntityService<CommandeClientDto> {
    CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande);

    CommandeClientDto updateClient(Long idCommande, Long idClient);

    CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande);

    CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande);

    CommandeClientDto findByCode(String code);

    List<LigneCommandeClientDto> findAllLigneCommandeClientByIdLigneCommandeClient(Long id);

}

