package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.CommandeClient;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Long id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;

    private Long idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        return CommandeClientDto.builder()
                                .id(commandeClient.getId())
                                .code(commandeClient.getCode())
                                .dateCommande(commandeClient.getDateCommande())
                                .etatCommande(commandeClient.getEtatCommande())
                                .client(ClientDto.fromEntity(commandeClient.getClient()))
                                .idEntreprise(commandeClient.getIdEntreprise())
                                .build();

    }

    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto == null) {
            return null;
        }
        return CommandeClient.builder().id(dto.getId())
                             .code(dto.getCode())
                             .client(ClientDto.toEntity(dto.getClient()))
                             .dateCommande(dto.getDateCommande())
                             .etatCommande(dto.getEtatCommande())
                             .idEntreprise(dto.getIdEntreprise()).build();
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
