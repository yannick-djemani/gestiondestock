package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.repository.ArticleRepository;
import com.afridevteam.gestionstock.repository.CommandeClientRepository;
import com.afridevteam.gestionstock.repository.FournisseurRepository;
import com.afridevteam.gestionstock.repository.LigneCommandeFounisseurRepository;
import com.afridevteam.gestionstock.service.CommandeFournisseurService;
import com.afridevteam.gestionstock.service.MvtStkService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("CommandeFournisseurService")
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final ArticleRepository articleRepository;
    private final LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository;
    private final CommandeClientRepository commandeClientRepository;
    private final FournisseurRepository fournisseurRepository;
    private final MvtStkService mvtStkService;

    public CommandeFournisseurServiceImpl(ArticleRepository articleRepository, LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository,
                                          CommandeClientRepository commandeClientRepository,
                                          FournisseurRepository fournisseurRepository,
                                          MvtStkService mvtStkService) {
        this.articleRepository = articleRepository;
        this.ligneCommandeFounisseurRepository = ligneCommandeFounisseurRepository;
        this.commandeClientRepository = commandeClientRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.mvtStkService = mvtStkService;
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
