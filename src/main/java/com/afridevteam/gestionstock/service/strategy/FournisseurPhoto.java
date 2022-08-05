package com.afridevteam.gestionstock.service.strategy;

import com.afridevteam.gestionstock.dto.FournisseurDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.service.FlickrService;
import com.afridevteam.gestionstock.service.FournisseurService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("FournisseurStrategy")
public class FournisseurPhoto implements PhotoStrategy<FournisseurDto> {
    private final FlickrService flickrService;
    private final FournisseurService fournisseurService;

    public FournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
        this.flickrService = flickrService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto UploadImage(Long id, InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseurDto = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasText(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseurDto.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseurDto);
    }

}
