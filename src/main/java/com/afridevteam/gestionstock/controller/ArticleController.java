package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.ArticleApi;
import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import com.afridevteam.gestionstock.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Long id) {
        articleService.delete(id);

    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        return articleService.findByCodeArticle(code);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Long idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandClient(Long idArticle) {
        return articleService.findHistoriqueCommandClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurs(Long idArticle) {
        return articleService.findHistoriqueCommandeFournisseurs(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticleByCategorie(Long idArticle) {
        return articleService.findAllArticleByCategorie(idArticle);
    }

    @Override
    public Page<ArticleDto> findAllArticleByCategorie(Long idArticle, Pageable pageable) {
        return articleService.findAllArticleByCategorie(idArticle, pageable);
    }
}
