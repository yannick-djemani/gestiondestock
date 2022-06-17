package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService extends EntityService<ArticleDto> {

    ArticleDto findByCodeArticle(String code);

    List<LigneVenteDto> findHistoriqueVentes(Long idArticle);

    List<LigneCommandeClientDto> findHistoriqueCommandClient(Long idArticle);

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurs(Long idArticle);

    List<ArticleDto> findAllArticleByCategorie(Long idArticle);

    Page<List<ArticleDto>> findAllArticleByCategorie(Long idArticle, Pageable pageable);

}
