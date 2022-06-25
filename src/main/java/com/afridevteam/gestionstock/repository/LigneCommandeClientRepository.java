package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {

    List<LigneCommandeClient> findAllByArticleId(Long idArticle);

    List<LigneCommandeClient> findAllByCommandeClientId(Long idCommande);
}
