package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.MvtStkDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

public interface MvstkApi {
    BigDecimal stockReelArticle(@PathVariable("id") Long idArticle);

    List<MvtStkDto> mvtStkArticle(@PathVariable("id") Long idArticle);

    MvtStkDto entreeStock(@RequestBody MvtStkDto dto);

    MvtStkDto sortieStock(@RequestBody MvtStkDto dto);

    MvtStkDto correctionStockPost(@RequestBody MvtStkDto dto);

    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto);
}
