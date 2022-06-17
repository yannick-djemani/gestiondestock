package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.ClientApi;
import com.afridevteam.gestionstock.dto.ClientDto;
import com.afridevteam.gestionstock.service.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {this.clientService = clientService;}


    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public ClientDto findByNom(String name) {
        return clientService.findByNom(name);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);

    }
}
