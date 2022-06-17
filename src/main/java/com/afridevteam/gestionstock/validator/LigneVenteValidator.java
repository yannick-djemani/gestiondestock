package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {
    public static List<String> validate(LigneVenteDto ligneVenteDto) {
        List<String> errors = new ArrayList<>();
        if (null == ligneVenteDto) {
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }
        if (null == ligneVenteDto.getQuantite()) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (null == ligneVenteDto.getPrixUnitaire()) {
            errors.add("Veuillez renseigner la quantite");
        }
        return errors;
    }
}
