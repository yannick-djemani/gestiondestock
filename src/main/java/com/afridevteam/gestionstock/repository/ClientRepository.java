package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNom(String name);
}
