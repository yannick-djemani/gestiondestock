package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Long id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Long idEntreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                        .id(client.getId())
                        .nom(client.getNom())
                        .prenom(client.getPrenom())
                        .adresse(AdresseDto.fromEntity(client.getAdresse()))
                        .photo(client.getPhoto())
                        .mail(client.getMail())
                        .numTel(client.getNumTel())
                        .idEntreprise(client.getIdEntreprise())
                        .build();
    }

    public static Client toEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        }
        return Client.builder().id(dto.getId())
                     .nom(dto.getNom())
                     .prenom(dto.getPrenom())
                     .adresse(AdresseDto.toEntity(dto.getAdresse()))
                     .photo(dto.getPhoto())
                     .mail(dto.getMail())
                     .numTel(dto.getNumTel())
                     .idEntreprise(dto.getIdEntreprise()).build();
    }

}
