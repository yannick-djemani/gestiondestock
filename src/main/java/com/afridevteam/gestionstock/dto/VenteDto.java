package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {

    private Long id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private List<LigneVenteDto> ligneVentes;
    private Long idEntreprise;

    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) {
            return null;
        }
        return VenteDto.builder()
                       .id(vente.getId())
                       .code(vente.getCode())
                       .commentaire(vente.getCommentaire())
                       .idEntreprise(vente.getIdEntreprise())
                       .build();
    }

    public static Vente toEntity(VenteDto dto) {
        if (dto == null) {
            return null;
        }
        return Vente.builder().id(dto.getId())
                    .code(dto.getCode())
                    .commentaire(dto.getCommentaire())
                    .idEntreprise(dto.getIdEntreprise()).build();
    }
}
