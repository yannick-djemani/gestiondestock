package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.MvtStk;
import com.afridevteam.gestionstock.domain.enumeration.SourceMvtStk;
import com.afridevteam.gestionstock.domain.enumeration.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Long id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvtStk;

    private SourceMvtStk sourceMvtStk;

    private Long idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if (mvtStk == null) {
            return null;
        }

        return MvtStkDto.builder()
                        .id(mvtStk.getId())
                        .dateMvt(mvtStk.getDateMvt())
                        .quantite(mvtStk.getQuantite())
                        .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                        .typeMvtStk(mvtStk.getTypeMvtStk())
                        .sourceMvtStk(mvtStk.getSourceMvtStk())
                        .idEntreprise(mvtStk.getIdEntreprise())
                        .build();
    }

    public static MvtStk toEntity(MvtStkDto dto) {
        if (dto == null) {
            return null;
        }

        return MvtStk.builder().id(dto.getId())
                     .dateMvt(dto.getDateMvt())
                     .quantite(dto.getQuantite())
                     .article(ArticleDto.toEntity(dto.getArticle()))
                     .typeMvtStk(dto.getTypeMvtStk())
                     .sourceMvtStk(dto.getSourceMvtStk())
                     .idEntreprise(dto.getIdEntreprise()).build();
    }
}
