package com.afridevteam.gestionstock.service.strategy;

import com.afridevteam.gestionstock.dto.EntrepriseDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.service.EntrepriseService;
import com.afridevteam.gestionstock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("EntrepriseStrategy")
public class EntreprisePhoto implements PhotoStrategy<EntrepriseDto> {
    private final FlickrService flickrService;
    private final EntrepriseService entrepriseService;

    public EntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto UploadImage(Long id, InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasText(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseDto);
    }

}
