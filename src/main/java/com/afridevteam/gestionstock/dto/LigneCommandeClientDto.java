package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Long id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Long idEntreprise;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                                     .id(ligneCommandeClient.getId())
                                     .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                                     .quantite(ligneCommandeClient.getQuantite())
                                     .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                                     .idEntreprise(ligneCommandeClient.getIdEntreprise())
                                     .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto dto) {
        if (dto == null) {
            return null;
        }

        return LigneCommandeClient.builder().id(dto.getId())
                                  .article(ArticleDto.toEntity(dto.getArticle()))
                                  .prixUnitaire(dto.getPrixUnitaire())
                                  .quantite(dto.getQuantite())
                                  .idEntreprise(dto.getIdEntreprise()).build();
    }

}
