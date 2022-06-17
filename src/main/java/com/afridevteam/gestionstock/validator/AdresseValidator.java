package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {
    public static List<String> validate(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();
        if (null == adresseDto) {
            errors.add("Veuillez enter l'adresse 1");
            errors.add("Veuillez enter la ville ");
            errors.add("Veuillez enter le pays ");
            errors.add("Veuillez enter le code postal");
            return errors;
        }
        if (!StringUtils.hasText(adresseDto.getAdresse1())) {
            errors.add("Veuillez enter l'adresse 1");
        }
        if (!StringUtils.hasText(adresseDto.getVille())) {
            errors.add("Veuillez enter la ville ");
        }
        if (!StringUtils.hasText(adresseDto.getPays())) {
            errors.add("Veuillez enter le pays ");
        }
        if (!StringUtils.hasText(adresseDto.getCodePostale())) {
            errors.add("Veuillez enter le code postal");
        }
        return errors;
    }
}
