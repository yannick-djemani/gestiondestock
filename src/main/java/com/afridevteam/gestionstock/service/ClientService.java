package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.ClientDto;

public interface ClientService extends EntityService<ClientDto> {
    ClientDto findByNom(String name);

}
