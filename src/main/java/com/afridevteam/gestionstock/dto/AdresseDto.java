package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Adresse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDto {

    private String adresse1;
    private String adresse2;
    private String ville;
    private String pays;
    private String codePostale;

    public static AdresseDto fromEntity(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        return AdresseDto.builder()
                         .adresse1(adresse.getAdresse1())
                         .adresse2(adresse.getAdresse2())
                         .pays(adresse.getPays())
                         .ville(adresse.getVille())
                         .codePostale(adresse.getCodePostale())
                         .build();
    }

    public static Adresse toEntity(AdresseDto adresseDto) {
        if (adresseDto == null) {
            return null;
        }
        return Adresse.builder()
                      .adresse1(adresseDto.getAdresse1())
                      .adresse2(adresseDto.getAdresse2())
                      .pays(adresseDto.getPays())
                      .ville(adresseDto.getVille())
                      .codePostale(adresseDto.getCodePostale())
                      .build();
    }

}
