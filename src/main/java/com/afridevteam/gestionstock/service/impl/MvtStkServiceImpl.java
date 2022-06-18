package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.MvtStkDto;
import com.afridevteam.gestionstock.service.MvtStkService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("MvtStkService")
public class MvtStkServiceImpl implements MvtStkService {

    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        return null;
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return null;
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto correctionStockPost(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return null;
    }
}
