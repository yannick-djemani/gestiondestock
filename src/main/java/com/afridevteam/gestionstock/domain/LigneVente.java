package com.afridevteam.gestionstock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lignevente")
public class LigneVente extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantite")
    private BigDecimal quantite;
    @Column(name = "prix_unitaire")
    private BigDecimal prixUnitaire;
    @Column(name = "id_entreprise")
    private Long idEntreprise;
    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
}
