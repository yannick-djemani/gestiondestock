package com.afridevteam.gestionstock.service.strategy;

import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.service.FlickrService;
import com.afridevteam.gestionstock.service.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("UtilisateurStrategy")
public class UtilisateurPhoto implements PhotoStrategy<UtilisateurDto> {
    private final FlickrService flickrService;
    private final UtilisateurService utilisateurService;

    public UtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto UploadImage(Long id, InputStream photo, String titre) throws FlickrException {

        UtilisateurDto utilisateurDto = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasText(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateurDto.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateurDto);
    }
}
