package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {
    public static List<String> validate(CategorieDto categorieDto) {
        List<String> errors = new ArrayList<>();
        if (null == categorieDto) {
            errors.add("Veuillez renseigner le code de la categorie");
            errors.add("Veuillez renseigner la designation");
            return errors;
        }

        if (!StringUtils.hasText(categorieDto.getCode())) {
            errors.add("Veuillez renseigner le code de la categorie");
        }
        if (!StringUtils.hasText(categorieDto.getDesignation())) {
            errors.add("Veuillez renseigner le code de la categorie");
        }
        return errors;
    }
}




