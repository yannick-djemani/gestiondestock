package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("gestionstock")
public class UtilisateurServiceImpl implements UtilisateurService {
    @Override
    public Optional<UtilisateurDto> save(UtilisateurDto dto) {
        return Optional.empty();
    }

    @Override
    public Optional<UtilisateurDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UtilisateurDto> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return null;
    }

    @Override
    public Optional<UtilisateurDto> changePassword() {
        return Optional.empty();
    }

    @Override
    public Optional<UtilisateurDto> currentUser() {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}