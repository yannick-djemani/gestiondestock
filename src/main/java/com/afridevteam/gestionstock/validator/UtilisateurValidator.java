package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();
        if (null == utilisateurDto) {
            errors.add("Veuillez renseigner le nom de l'utilisateur ");
            errors.add("Veuillez renseigner le prenom de l'utilisateur ");
            errors.add("Veuillez renseigner le email de l'utilisateur ");
            errors.add("Veuillez renseigner le email de l'utilisateur ");
            errors.add("Veuillez renseigner le email de l'utilisateur ");
            errors.add("Veuillez renseigner la photo photo ");
            errors.add("Veuillez renseigner le role de l'utilisateur ");
            return errors;
        }
        if (!StringUtils.hasText(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur ");
        }
        if (!StringUtils.hasText(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom de l'utilisateur ");
        }
        if (!StringUtils.hasText(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner le email de l'utilisateur ");
        }
        if (!StringUtils.hasText(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe  de l'utilisateur ");
        }
        if (null == utilisateurDto.getDateNaissance()) {
            errors.add("Veuillez renseigner la date de naissance");
        }
        if (!StringUtils.hasText(utilisateurDto.getPhoto())) {
            errors.add("Veuillez renseigner la photo du client ");
        }
        return errors;
    }
}
