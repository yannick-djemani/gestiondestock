package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.ClientDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.ClientRepository;
import com.afridevteam.gestionstock.service.ClientService;
import com.afridevteam.gestionstock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("ClientService")
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {this.clientRepository = clientRepository;}

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("client  is invalid {}", dto);
            throw new InvalidEntityException(" Le client n'est pas valide ", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));

    }

    @Override
    public ClientDto findById(Long id) {
        if (null == id) {
            log.error(" l'id du client ne doit pas etre null");
            return null;
        }
        return clientRepository.findById(id)
                               .map(ClientDto::fromEntity)
                               .orElseThrow(() -> new EntityNotFoundException("Aucun client  avec l'id=" + id + " n'a ete trouve da la bd ",
                                                                              ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public ClientDto findByNom(String name) {
        if (!StringUtils.hasText(name)) {
            log.error("Client name  est null");
        }
        return clientRepository.findByNom(name).map(ClientDto::fromEntity)
                               .orElseThrow(() -> new EntityNotFoundException("Aucun client  avec le nom=" + name + " n'a ete trouve da la bd ",
                                                                              ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        //verifier que le client est dans commande client avant de le supprimer
        if (null == id) {
            log.error(" impossible de supprimer le client ");
            throw new InvalidOperationException("impossible de supprimer ce client avec un id null", ErrorCodes.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }
}