package com.afridevteam.gestionstock.service;

import com.afridevteam.gestionstock.dto.CategorieDto;

public interface CategorieService extends EntityService<CategorieDto> {
    
    CategorieDto findByCode(String code);

}
