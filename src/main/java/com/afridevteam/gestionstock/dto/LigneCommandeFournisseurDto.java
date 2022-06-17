package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.CommandeFournisseur;
import com.afridevteam.gestionstock.domain.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Long id;

    private ArticleDto article;

    private CommandeFournisseur commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Long idEntreprise;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                                          .id(ligneCommandeFournisseur.getId())
                                          .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                                          .quantite(ligneCommandeFournisseur.getQuantite())
                                          .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                                          .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                                          .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }

        return LigneCommandeFournisseur.builder().id(dto.getId())
                                       .article(ArticleDto.toEntity(dto.getArticle()))
                                       .prixUnitaire(dto.getPrixUnitaire())
                                       .quantite(dto.getQuantite())
                                       .idEntreprise(dto.getIdEntreprise()).build();
    }

}
