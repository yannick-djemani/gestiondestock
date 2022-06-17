package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();
        if (null == fournisseurDto) {
            errors.add("Veuillez renseigner  le nom du fournisseur");
            errors.add("Veuillez renseigner le nom de le prenom");
            errors.add("Veuillez renseigner la photo");
            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le numero de telephone");

            return errors;
        }
        if (!StringUtils.hasText(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner  le nom de l'entreprise");
        }
        if (!StringUtils.hasText(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner  le prenom");
        }
        if (!StringUtils.hasText(fournisseurDto.getPhoto())) {
            errors.add("Veuillez renseigner le nom de la photo");
        }
        if (!StringUtils.hasText(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner l'email");
        }
        if (!StringUtils.hasText(fournisseurDto.getNumTel())) {
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");
        }
        return errors;
    }
}
