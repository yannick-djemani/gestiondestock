package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.LigneCommandeClient;
import com.afridevteam.gestionstock.domain.LigneCommandeFournisseur;
import com.afridevteam.gestionstock.domain.LigneVente;
import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.repository.ArticleRepository;
import com.afridevteam.gestionstock.repository.LigneCommandeClientRepository;
import com.afridevteam.gestionstock.repository.LigneCommandeFounisseurRepository;
import com.afridevteam.gestionstock.repository.LigneVenteRepository;
import com.afridevteam.gestionstock.service.ArticleService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("ArticleService")
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    private final LigneVenteRepository ligneVenteRepository;

    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    private final LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository;

    public ArticleServiceImpl(
        ArticleRepository articleRepository,
        LigneVenteRepository ligneVenteRepository,
        LigneCommandeClientRepository ligneCommandeClientRepository,
        LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository) {
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.ligneCommandeFounisseurRepository = ligneCommandeFounisseurRepository;
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        if (!StringUtils.hasText(code)) {
            log.error("Le code ne doit pas etre null ");
            return null;
        }
        return articleRepository.findByCodeArticle(code)
                                .map(ArticleDto::fromEntity)
                                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "article", "le code", code),
                                                                               ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Long idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle)
                                   .stream().map(LigneVenteDto::fromEntity)
                                   .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandClient(Long idArticle) {
        return ligneCommandeClientRepository.findAllByArticleId(idArticle)
                                            .stream()
                                            .map(LigneCommandeClientDto::fromEntity)
                                            .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurs(Long idArticle) {
        return ligneCommandeFounisseurRepository.findAllByArticleId(idArticle)
                                                .stream()
                                                .map(LigneCommandeFournisseurDto::fromEntity)
                                                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByCategorie(Long idArticle) {
        return articleRepository.findByCategorieId(idArticle)
                                .stream()
                                .map(ArticleDto::fromEntity)
                                .collect(Collectors.toList());
    }

    @Override
    public Page<ArticleDto> findAllArticleByCategorie(Long idArticle, Pageable pageable) {

        return articleRepository.findByCodeArticle(idArticle, pageable).map(ArticleDto::fromEntity);
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("client  is invalid {}", dto);
            throw new InvalidEntityException(" Le client n'est pas valide ", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Long id) {
        if (null == id) {
            log.error(" l'id du client ne doit pas etre null");
            return null;
        }
        return articleRepository.findById(id)
                                .map(ArticleDto::fromEntity)
                                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "article", "l'id", id.toString()),
                                                                               ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll()
                                .stream()
                                .map(ArticleDto::fromEntity)
                                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("l'id de l'article ne doit etre null");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByArticleId(id);
        if (!ligneCommandeClients.isEmpty()) {
            throw new InvalidEntityException("Impossible de supprimer un article dans commande client", ErrorCodes.ARTICLE_ALREADY_IN_USE);

        }
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFounisseurRepository.findAllByArticleId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidEntityException("Impossible de supprimer un article dans  fournisseur", ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByArticleId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidEntityException("Impossible de supprimer un article dans  les ventes", ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        articleRepository.deleteById(id);
    }
}
