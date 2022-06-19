package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.VenteDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.VenteRepository;
import com.afridevteam.gestionstock.service.VenteService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("Ventes")
@Slf4j
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;

    public VenteServiceImpl(VenteRepository venteRepository) {this.venteRepository = venteRepository;}

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is invalid {}", dto);
            throw new InvalidEntityException("Veuillez corriger les erreurs ", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        return VenteDto.fromEntity(venteRepository.save(VenteDto.toEntity(dto)));

    }

    @Override
    public VenteDto findById(Long id) {
        if (null == id) {
            log.error("l'id ne doit pas etre null");
            return null;
        }
        return venteRepository.findById(id)
                              .map(VenteDto::fromEntity)
                              .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "vente", "l'id", id.toString()),
                                                                             ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll()
                              .stream()
                              .map(VenteDto::fromEntity)
                              .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("impossible de supprimer une vente qui n'a pas id");
            throw new InvalidOperationException("impossible de supprimer la vente   id null", ErrorCodes.CLIENT_NOT_FOUND);
        }
        venteRepository.deleteById(id);

    }

    @Override
    public VenteDto findByCode(String code) {
        return null;
    }
}
