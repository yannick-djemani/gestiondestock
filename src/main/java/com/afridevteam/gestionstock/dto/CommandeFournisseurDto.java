package com.afridevteam.gestionstock.dto;

import com.afridevteam.gestionstock.domain.CommandeFournisseur;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Long id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private FournisseurDto fournisseur;

    private Long idEntreprise;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
        }
        return CommandeFournisseurDto.builder()
                                     .id(commandeFournisseur.getId())
                                     .code(commandeFournisseur.getCode())
                                     .dateCommande(commandeFournisseur.getDateCommande())
                                     .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                                     .etatCommande(commandeFournisseur.getEtatCommande())
                                     .idEntreprise(commandeFournisseur.getIdEntreprise())
                                     .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        return CommandeFournisseur.builder().id(dto.getId())
                                  .code(dto.getCode())
                                  .dateCommande(dto.getDateCommande())
                                  .fournisseur(FournisseurDto.toEntity(dto.getFournisseur()))
                                  .idEntreprise(dto.getIdEntreprise())
                                  .etatCommande(dto.getEtatCommande()).build();
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }

}
