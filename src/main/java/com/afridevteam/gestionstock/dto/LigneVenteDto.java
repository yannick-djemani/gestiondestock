package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Long id;

    private VenteDto vente;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Long idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            return null;
        }

        return LigneVenteDto.builder()
                            .id(ligneVente.getId())
                            .vente(VenteDto.fromEntity(ligneVente.getVente()))
                            .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                            .quantite(ligneVente.getQuantite())
                            .prixUnitaire(ligneVente.getPrixUnitaire())
                            .idEntreprise(ligneVente.getIdEntreprise())
                            .build();
    }

    public static LigneVente toEntity(LigneVenteDto dto) {
        if (dto == null) {
            return null;
        }
        return LigneVente.builder().id(dto.getId())
                         .vente(VenteDto.toEntity(dto.getVente()))
                         .article(ArticleDto.toEntity(dto.getArticle()))
                         .quantite(dto.getQuantite())
                         .prixUnitaire(dto.getPrixUnitaire())
                         .idEntreprise(dto.getIdEntreprise()).build();
    }

}
