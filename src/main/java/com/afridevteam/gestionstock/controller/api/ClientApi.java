package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.CLIENT_ENPOINT;
import static com.afridevteam.gestionstock.utils.Constants.CLIENT_ENPOINT_BY_ID;
import static com.afridevteam.gestionstock.utils.Constants.CLIENT_ENPOINT_BY_NAME;

public interface ClientApi {
    @PostMapping(value = CLIENT_ENPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(CLIENT_ENPOINT_BY_ID)
    ClientDto findById(@PathVariable("id") Long id);

    @GetMapping(CLIENT_ENPOINT_BY_NAME)
    ClientDto findByNom(@PathVariable("name") String name);

    @GetMapping(CLIENT_ENPOINT)
    List<ClientDto> findAll();

    @DeleteMapping(CLIENT_ENPOINT_BY_ID)
    void delete(@PathVariable("id") Long id);
}
