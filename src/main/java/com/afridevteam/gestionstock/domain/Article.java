package com.afridevteam.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_article")
    private String codeArticle;

    @NotBlank(message = "La designation de l'article ne doit pas etre null")
    @Column(name = "designation")
    private String designation;

    @NotNull(message = "Le prix unitaire ht de l'article ne doit pas etre null")
    @Column(name = "prix_unitaire_ht")
    private BigDecimal prixUnitaireHt;

    @NotNull(message = "Le prix unitaire ttc de l'article ne doit pas etre null")
    @Column(name = "prix_unitaire_ttc")
    private BigDecimal prixUnitaireTtc;

    @NotNull(message = "Le taux unitaire ht de l'article ne doit pas etre null")
    @Column(name = "taux_tva")
    private BigDecimal tauxTva;

    @NotBlank(message = " La photo ne doit etre vide , veuillez choisir une photo")
    @Column(name = "photo")
    private String photo;

    @Column(name = "id_entreprise")
    private Long idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<LigneVente> ligneVentes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<MvtStk> mvtStks;

}
