package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {

    List<LigneVente> findAllByArticleId(Long idArticle);
}
