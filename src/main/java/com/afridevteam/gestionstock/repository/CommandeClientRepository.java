package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

    List<CommandeClient> findAllByClientId(Long id);

    Optional<CommandeClient> findByCode(String code);
}
