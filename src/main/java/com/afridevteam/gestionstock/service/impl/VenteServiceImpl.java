package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.VenteDto;
import com.afridevteam.gestionstock.service.VenteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Ventes")
public class VenteServiceImpl implements VenteService {
    @Override
    public VenteDto save(VenteDto dto) {
        return null;
    }

    @Override
    public VenteDto findById(Long id) {
        return null;
    }

    @Override
    public List<VenteDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public VenteDto findByCode(String code) {
        return null;
    }
}
