package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {

    List<CommandeFournisseur> findAllByFournisseurId(Long id);

    Optional<CommandeFournisseur> findByCode(String code);


}
