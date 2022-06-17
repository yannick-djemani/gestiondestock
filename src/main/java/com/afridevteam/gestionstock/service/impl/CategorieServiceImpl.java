package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.Article;
import com.afridevteam.gestionstock.dto.CategorieDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.mapper.CategorieMapper;
import com.afridevteam.gestionstock.repository.ArticleRepository;
import com.afridevteam.gestionstock.repository.CategorieRepository;
import com.afridevteam.gestionstock.service.CategorieService;
import com.afridevteam.gestionstock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("CategorieService")
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    private final ArticleRepository articleRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository, ArticleRepository articleRepository) {
        this.categorieRepository = categorieRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        List<String> errors = CategorieValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article invalid{}", dto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        //appel avec Mapstruct pour convertir
        return CategorieMapper.INSTANCE.toDto(categorieRepository.save(CategorieMapper.INSTANCE.toEntity(dto)));
        // return CategorieDto.fromEntity(categorieRepository.save(CategorieDto.toEntity(dto)));
    }

    @Override
    public CategorieDto findById(Long id) {
        if (null == id) {
            log.error("Categorie id est null");
            return null;
        }
        return categorieRepository.findById(id)
                                  .map(CategorieDto::fromEntity)
                                  .orElseThrow(() -> new EntityNotFoundException("Aucune categorie avec l'id=" + id + " n'a ete trouve da la bd ",
                                                                                 ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategorieDto findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            log.error("Categorie code est null");
        }
        return categorieRepository.findByCode(code)
                                  .map(CategorieDto::fromEntity)
                                  .orElseThrow(() -> new EntityNotFoundException("Aucune categorie avec le code =" + code + " n'a ete trouve da la bd ",
                                                                                 ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream().map(CategorieDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("Categorie id est null");
        }
        List<Article> list = articleRepository.findByCategorieId(id);
        if (!list.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilise", ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }
        categorieRepository.deleteById(id);
    }
}
