package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.service.CommandeFournisseurService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("CommandeFournisseurService")
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
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
