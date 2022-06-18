package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFounisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {

    List<LigneCommandeFournisseur> findAllByArticleId(Long idArticle);
}
