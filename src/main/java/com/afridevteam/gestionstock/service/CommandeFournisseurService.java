package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto updateEtatCommande(Long idCommande, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande);

    CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur);

    CommandeFournisseurDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande);

    CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande);

    CommandeFournisseurDto findByCode(String code);

    List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByIdLigneCommandeFournisseur(Long id);
}
