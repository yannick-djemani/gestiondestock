package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.CommandeFournisseur;
import com.afridevteam.gestionstock.dto.FournisseurDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.CommandeClientRepository;
import com.afridevteam.gestionstock.repository.CommandeFournisseurRepository;
import com.afridevteam.gestionstock.repository.FournisseurRepository;
import com.afridevteam.gestionstock.service.FournisseurService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("FournisseurService")
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository founisseurRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    public FournisseurServiceImpl(
        FournisseurRepository founisseurRepository,
        CommandeClientRepository commandeClientRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.founisseurRepository = founisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;

    }


    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("client  is invalid {}", dto);
            throw new InvalidEntityException(" Le Fournisseur n'est pas valide ", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(founisseurRepository.save(FournisseurDto.toEntity(dto)));

    }


    @Override
    public FournisseurDto findById(Long id) {
        if (null == id) {
            log.error("l'id du fournisseur ne doit pas etre null ");
            return null;
        }
        return founisseurRepository.findById(id)
                                   .map(FournisseurDto::fromEntity)
                                   .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M,
                                                                                                "fournisseur",
                                                                                                "l'id",
                                                                                                id.toString(),
                                                                                                ErrorCodes.FOURNISSEUR_NOT_FOUND)));

    }

    @Override
    public List<FournisseurDto> findAll() {
        return founisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("l'id du fournisseur ne doit pas etre null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("impossible de supprimerun fournisseur qui a deja des commandes", ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        founisseurRepository.deleteById(id);
    }
}
