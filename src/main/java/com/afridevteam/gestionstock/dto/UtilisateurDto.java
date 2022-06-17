package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UtilisateurDto {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateNaissance;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                             .id(utilisateur.getId())
                             .nom(utilisateur.getNom())
                             .prenom(utilisateur.getPrenom())
                             .email(utilisateur.getEmail())
                             .motDePasse(utilisateur.getMotDePasse())
                             .dateNaissance(utilisateur.getDateNaissance())
                             .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                             .photo(utilisateur.getPhoto())
                             .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                             .roles(
                                 utilisateur.getRoles() != null ?
                                 utilisateur.getRoles().stream()
                                            .map(RolesDto::fromEntity)
                                            .collect(Collectors.toList()) : null
                             )
                             .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        }
        return Utilisateur.builder().id(dto.getId())
                          .nom(dto.getNom())
                          .prenom(dto.getPrenom())
                          .email(dto.getEmail())
                          .motDePasse(dto.getMotDePasse())
                          .dateNaissance(dto.getDateNaissance())
                          .adresse(AdresseDto.toEntity(dto.getAdresse()))
                          .photo(dto.getPhoto())
                          .entreprise(EntrepriseDto.toEntity(dto.getEntreprise())).build();
    }
}
