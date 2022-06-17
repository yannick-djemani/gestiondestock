package com.afridevteam.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class Adresse implements Serializable {

    @Column(name = "adresse1")
    @NotBlank(message = "L'adresse 1 ne doit pas etre vide")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    @NotBlank(message = "La ville est un champs obligatoire")
    private String ville;

    @Column(name = "code_postale")
    @NotBlank(message = "Ce champs obligatoire")
    private String codePostale;

    @Column(name = "pays")
    @NotBlank(message = "Ce champs obligatoire")
    private String pays;
}
