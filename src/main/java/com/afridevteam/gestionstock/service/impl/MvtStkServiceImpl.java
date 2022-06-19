package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.enumeration.TypeMvtStk;
import com.afridevteam.gestionstock.dto.MvtStkDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.repository.MvtStkRepository;
import com.afridevteam.gestionstock.service.ArticleService;
import com.afridevteam.gestionstock.service.MvtStkService;
import com.afridevteam.gestionstock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("MvtStkService")
public class MvtStkServiceImpl implements MvtStkService {
    private final MvtStkRepository mvtStkRepository;
    private final ArticleService articleService;

    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ArticleService articleService) {
        this.mvtStkRepository = mvtStkRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        if (null == idArticle) {
            log.error("l'id de l'article etre null");
            log.warn("Default value {}", BigDecimal.valueOf(-1));
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStkRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return mvtStkRepository.findAllByArticleId(idArticle).stream().map(MvtStkDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return entreePositive(dto, TypeMvtStk.ENTREE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return sortieNegative(dto, TypeMvtStk.SORTIE);
    }

    @Override
    public MvtStkDto correctionStockPost(MvtStkDto dto) {
        return entreePositive(dto, TypeMvtStk.CORRECTION_POS);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return sortieNegative(dto, TypeMvtStk.CORRECTION_NEG);
    }

    private MvtStkDto sortieNegative(MvtStkDto dto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("l'article est invalide {} ", dto);
            throw new InvalidEntityException("Le mvt de stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setTypeMvtStk(typeMvtStk);
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue() * -1)));
        return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.toEntity(dto)));
    }

    private MvtStkDto entreePositive(MvtStkDto dto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("l'article est invalide {} ", dto);
            throw new InvalidEntityException("Le mvt de stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setTypeMvtStk(typeMvtStk);
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
        return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.toEntity(dto)));
    }
}
