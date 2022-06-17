package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Optional<Categorie> findByCode(String code);
}
