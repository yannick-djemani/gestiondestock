package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();
        if (null != commandeFournisseurDto) {
            if (!StringUtils.hasText(commandeFournisseurDto.getCode())) {
                errors.add("Veuillez renseigner le code ");
            }
            if (!StringUtils.hasText(commandeFournisseurDto.getCode())) {
                errors.add("Veuillez renseigner l'etat de la commande ");
            }
        } else {
            errors.add("Veuillez renseigner le code ");
            errors.add("Veuillez renseigner l'etat de la commande ");
        }
        return errors;
    }
}
