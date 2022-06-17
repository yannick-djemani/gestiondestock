package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import com.afridevteam.gestionstock.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Override
    public ArticleDto findByCodeArticle(String code) {
        return null;
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Long idArticle) {
        return null;
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandClient(Long idArticle) {
        return null;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurs(Long idArticle) {
        return null;
    }

    @Override
    public List<ArticleDto> findAllArticleByCategorie(Long idArticle) {
        return null;
    }

    @Override
    public Page<List<ArticleDto>> findAllArticleByCategorie(Long idArticle, Pageable pageable) {
        return null;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return null;
    }

    @Override
    public ArticleDto findById(Long id) {
        return null;
    }

    @Override
    public List<ArticleDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
