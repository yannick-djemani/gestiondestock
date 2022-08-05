package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientApi {
    CommandeClientDto findById(Long id);


    CommandeClientDto save(CommandeClientDto dto);


    List<CommandeClientDto> findAll();


    void delete(Long id);

    CommandeClientDto findByCode(String code);

    CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande);

    CommandeClientDto updateClient(Long idCommande, Long idClient);

    CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande);

    CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande);

    List<LigneCommandeClientDto> findAllLigneCommandeClientByIdLigneCommandeClient(Long id);


}
