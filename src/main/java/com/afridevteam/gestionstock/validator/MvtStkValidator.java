package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();
        if (null == mvtStkDto) {
            errors.add(" Veuillez renseigner la quantite ");
            errors.add(" Veuillez renseigner l'article  ");
            errors.add(" Veuillez renseigner le type de mvtStk ");
            errors.add(" Veuillez renseigner le type  sourceMvtStk ");
            return errors;
        }
        if (null == mvtStkDto.getQuantite()) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (null == mvtStkDto.getTypeMvtStk()) {
            errors.add(" Veuillez renseigner le type de mvtStk ");
        }
        if (null == mvtStkDto.getSourceMvtStk()) {
            errors.add(" Veuillez renseigner le type  sourceMvtStk ");
        }
        if (!StringUtils.hasText(mvtStkDto.getArticle().getCodeArticle())) {
            errors.add("Veuillez renseigner le code de l'article ");
        }
        return errors;
    }
}
