package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList();
        if (null == commandeClientDto) {
            errors.add("Veuillez renseigner le code ");
            errors.add("Veuillez renseigner l'etat de la commande ");
            return errors;
        }

        if (!StringUtils.hasText(commandeClientDto.getCode())) {
            errors.add("Veuillez renseigner le code ");
        }
        if (!StringUtils.hasText(commandeClientDto.getCode())) {
            errors.add("Veuillez renseigner l'etat de la commande ");
        }

        return errors;
    }
}
