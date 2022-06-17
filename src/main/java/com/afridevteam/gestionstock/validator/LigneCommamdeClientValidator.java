package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommamdeClientValidator {
    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = new ArrayList<>();
        if (null == ligneCommandeClientDto) {
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
        }
        if (null == ligneCommandeClientDto.getQuantite()) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (null == ligneCommandeClientDto.getPrixUnitaire()) {
            errors.add("Veuillez renseigner la quantite");
        }
        return errors;
    }
}
