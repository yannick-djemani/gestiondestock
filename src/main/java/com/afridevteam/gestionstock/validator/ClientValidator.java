package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList();
        if (null == clientDto) {
            errors.add("Veuillez renseigner le nom du client ");
            errors.add("Veuillez renseigner le prenom du client ");
            errors.add("Veuillez renseigner le mail du client ");
            errors.add("Veuillez renseigner le numero de telephone du client ");
            errors.add("Veuillez renseigner la photo du client ");
            return errors;
        }
        if (!StringUtils.hasText(clientDto.getNom())) {
            errors.add("Veuillez renseigner le nom du client ");
        }
        if (!StringUtils.hasText(clientDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du client ");
        }
        if (!StringUtils.hasText(clientDto.getMail())) {
            errors.add("Veuillez renseigner le mail du client ");
        }
        if (null == clientDto.getNumTel()) {
            errors.add("Veuillez renseigner le numero de telephone du client");
        }

        if (!StringUtils.hasText(clientDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo du client ");
        }
        return errors;
    }
}
