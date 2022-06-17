package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Categorie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorieDto {

    private Long id;
    private String code;
    private String designation;
    private Long idEntreprise;

    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        return CategorieDto.builder()
                           .id(categorie.getId())
                           .code(categorie.getCode())
                           .designation(categorie.getDesignation())
                           .idEntreprise(categorie.getIdEntreprise())
                           .build();
    }

    public static Categorie toEntity(CategorieDto categorieDto) {
        if (categorieDto == null) {
            return null;
        }
        return Categorie.builder()
                        .id(categorieDto.getId())
                        .code(categorieDto.getCode())
                        .designation(categorieDto.getDesignation())
                        .idEntreprise(categorieDto.getIdEntreprise())
                        .build();
    }
}
