package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

    List<CommandeClient> findAllByClientId(Long id);
}
