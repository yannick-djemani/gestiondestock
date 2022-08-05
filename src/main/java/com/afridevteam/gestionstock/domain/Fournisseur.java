package com.afridevteam.gestionstock.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "code")
    private String code;
    @Embedded
    private Adresse adresse;
    @Column(name = "photo")
    private String photo;
    @Column(name = "mail")
    private String mail;
    @Column(name = "num_tel")
    private String numTel;
    @Column(name = "id_entreprise")
    private Long idEntreprise;

}
