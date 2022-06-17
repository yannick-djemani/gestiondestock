package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {
    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        List<String> errors = new ArrayList<>();
        if (null == ligneCommandeFournisseurDto) {
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }
        if (null == ligneCommandeFournisseurDto.getQuantite()) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (null == ligneCommandeFournisseurDto.getPrixUnitaire()) {
            errors.add("Veuillez renseigner la quantite");
        }
        return errors;
    }
}
