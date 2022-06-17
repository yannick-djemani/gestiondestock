package com.afridevteam.gestionstock.validator;

import com.afridevteam.gestionstock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();
        if (articleDto == null) {
            errors.add("Veuillez renseigner  le code de l'article");
            errors.add("Veuillez enter la designation");
            errors.add("Veuillez renseigner le prix unitataire HT de l'article ");
            errors.add("Veuillez renseigner le  taux tva de l'article");
            errors.add("Veuillez renseigner le  prix unitaire TTC  de l'article");
            errors.add("Veuillez renseigner la categorie");
            return errors;
        }
        if (!StringUtils.hasText(articleDto.getCodeArticle())) {
            errors.add("Veuillez renseigner  le code de l'article");
        }
        if (!StringUtils.hasText(articleDto.getCodeArticle())) {
            errors.add("Veuillez renseigner  la designation  de l'article");
        }
        if (null == articleDto.getPrixUnitaireHt()) {
            errors.add("Veuillez renseigner le  taux tva de l'article");
        }
        if (null == articleDto.getPrixUnitaireTtc()) {
            errors.add("Veuillez renseigner le  prix unitaire TTC  de l'article");
        }
        if (null == articleDto.getTauxTva()) {
            errors.add("Veuillez renseigner le  taux tva de l'article");
        }
        if (null == articleDto.getCodeArticle()) {
            errors.add("Veuillez renseigner la categorie");
        }

        return errors;
    }
}
