package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente, Long> {

    Optional<Vente> findVenteByCode(String code);
}
