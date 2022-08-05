package com.afridevteam.gestionstock.service.strategy;

import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.service.ArticleService;
import com.afridevteam.gestionstock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Slf4j
@Service("articleStrategy")
public class ArticlePhoto implements PhotoStrategy<ArticleDto> {
    private final FlickrService flickrService;
    private final ArticleService articleService;

    public ArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto UploadImage(Long id, InputStream photo, String titre) throws FlickrException {

        ArticleDto articleDto = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasText(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        articleDto.setPhoto(urlPhoto);
        return articleService.save(articleDto);
    }
}
