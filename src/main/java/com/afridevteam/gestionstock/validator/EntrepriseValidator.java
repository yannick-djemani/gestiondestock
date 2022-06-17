package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();
        if (null == entrepriseDto) {
            errors.add("Veuillez renseigner  le nom de l'entreprise");
            errors.add("Veuillez renseigner le nom de le codeFiscal");
            errors.add("Veuillez renseigner la photo");
            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
            errors.add("Veuillez renseigner le site web ");
            return errors;
        }

        if (!StringUtils.hasText(entrepriseDto.getNom())) {
            errors.add("Veuillez renseigner  le nom de l'entreprise");
        }
        if (!StringUtils.hasText(entrepriseDto.getDescription())) {
            errors.add("Veuillez renseigner  la description");
        }
        if (!StringUtils.hasText(entrepriseDto.getCodeFiscal())) {
            errors.add("Veuillez renseigner le nom de le codeFiscal");
        }
        if (!StringUtils.hasText(entrepriseDto.getEmail())) {
            errors.add("Veuillez renseigner l'email");
        }
        if (!StringUtils.hasText(entrepriseDto.getNumTel())) {
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
        }
        if (!StringUtils.hasText(entrepriseDto.getSiteWeb())) {
            errors.add("Veuillez renseigner le site web ");
        }
        return errors;

    }
}
