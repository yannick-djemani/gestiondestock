package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.MvtStkDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {
    BigDecimal stockReelArticle(Long idArticle);

    List<MvtStkDto> mvtStkArticle(Long idArticle);

    MvtStkDto entreeStock(MvtStkDto dto);

    MvtStkDto sortieStock(MvtStkDto dto);

    MvtStkDto correctionStockPost(MvtStkDto dto);

    MvtStkDto correctionStockNeg(MvtStkDto dto);

}
