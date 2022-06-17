package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {

    private Long id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal prixUnitaireTtc;

    private BigDecimal tauxTva;

    private String photo;

    private Long idEntreprise;

    private CategorieDto categorieDto;


    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleDto.builder()
                         .id(article.getId())
                         .codeArticle(article.getCodeArticle())
                         .designation(article.getDesignation())
                         .prixUnitaireHt(article.getPrixUnitaireHt())
                         .prixUnitaireTtc(article.getPrixUnitaireTtc())
                         .tauxTva(article.getTauxTva())
                         .photo(article.getPhoto())
                         .idEntreprise(article.getIdEntreprise())
                         .categorieDto(CategorieDto.fromEntity(article.getCategorie()))
                         .build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
        }
        return Article.builder()
                      .id(articleDto.getId())
                      .codeArticle(articleDto.getCodeArticle())
                      .designation(articleDto.getDesignation())
                      .prixUnitaireHt(articleDto.getPrixUnitaireHt())
                      .prixUnitaireTtc(articleDto.getPrixUnitaireTtc())
                      .tauxTva(articleDto.getTauxTva())
                      .photo(articleDto.getPhoto())
                      .idEntreprise(articleDto.getIdEntreprise())
                      .categorie(CategorieDto.toEntity(articleDto.getCategorieDto()))
                      .build();
    }

}
