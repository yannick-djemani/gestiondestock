package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Entreprise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EntrepriseDto {

    private Long id;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;


    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }
        return EntrepriseDto.builder()
                            .id(entreprise.getId())
                            .nom(entreprise.getNom())
                            .description(entreprise.getDescription())
                            .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                            .codeFiscal(entreprise.getCodeFiscal())
                            .photo(entreprise.getPhoto())
                            .email(entreprise.getEmail())
                            .numTel(entreprise.getNumTel())
                            .siteWeb(entreprise.getSiteWeb())
                            .build();
    }

    public static Entreprise toEntity(EntrepriseDto dto) {
        if (dto == null) {
            return null;
        }
        return Entreprise.builder().id(dto.getId())
                         .nom(dto.getNom())
                         .description(dto.getDescription())
                         .adresse(AdresseDto.toEntity(dto.getAdresse()))
                         .codeFiscal(dto.getCodeFiscal())
                         .photo(dto.getPhoto())
                         .email(dto.getEmail())
                         .numTel(dto.getNumTel())
                         .siteWeb(dto.getSiteWeb()).build();

    }

}
