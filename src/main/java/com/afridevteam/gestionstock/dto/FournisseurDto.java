package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Long id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Long idEntreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }
        return FournisseurDto.builder()
                             .id(fournisseur.getId())
                             .nom(fournisseur.getNom())
                             .prenom(fournisseur.getPrenom())
                             .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                             .photo(fournisseur.getPhoto())
                             .mail(fournisseur.getMail())
                             .numTel(fournisseur.getNumTel())
                             .idEntreprise(fournisseur.getIdEntreprise())
                             .build();
    }

    public static Fournisseur toEntity(FournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        return Fournisseur.builder().id(dto.getId())
                          .nom(dto.getNom())
                          .prenom(dto.getPrenom())
                          .adresse(AdresseDto.toEntity(dto.getAdresse()))
                          .photo(dto.getPhoto())
                          .mail(dto.getMail())
                          .numTel(dto.getNumTel())
                          .idEntreprise(dto.getIdEntreprise()).build();
    }
}
