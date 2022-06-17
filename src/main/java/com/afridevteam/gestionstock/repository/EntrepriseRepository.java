package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    Optional<Entreprise> findByNom(String name);
}
