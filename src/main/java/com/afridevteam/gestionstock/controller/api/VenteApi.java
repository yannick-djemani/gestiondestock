package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.VenteDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface VenteApi {

    VenteDto save(VenteDto dto);

    VenteDto findById(@PathVariable("id") Long id);

    List<VenteDto> findAll();

    void delete(Long id);

    VenteDto findByCode(String code);
}
