package com.afridevteam.gestionstock.mapper;

import com.afridevteam.gestionstock.domain.Categorie;
import com.afridevteam.gestionstock.dto.CategorieDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper {

    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);

    CategorieDto toDto(Categorie categorie);

    Categorie toEntity(CategorieDto categorieDto);
}
