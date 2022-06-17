package com.afridevteam.gestionstock.mapper;

import com.afridevteam.gestionstock.domain.Article;
import com.afridevteam.gestionstock.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {CategorieMapper.class})
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "categorieDto", source = "categorie")
    ArticleDto toDto(Article article);

    @Mapping(target = "categorie", source = "categorieDto")
    Article toEntity(ArticleDto articleDto);

    @Mapping(target = "categorie", source = "categorieDto")
    Set<Article> toSet(List<ArticleDto> list);
}
