package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.service.strategy.*;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class PhotoStrategyContext {
    private final BeanFactory beanFactory;
    private PhotoStrategy photoStrategy;
    private String context;

    public PhotoStrategyContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Long id, InputStream photo, String titre) throws FlickrException {
        determinContext(context);
        return photoStrategy.UploadImage(id, photo, titre);
    }

    private void determinContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "article":
                photoStrategy = beanFactory.getBean(beanName, ArticlePhoto.class);
                break;
            case "client":
                photoStrategy = beanFactory.getBean(beanName, ClientPhoto.class);
                break;
            case "fournisseur":
                photoStrategy = beanFactory.getBean(beanName, FournisseurPhoto.class);
                break;
            case "entreprise":
                photoStrategy = beanFactory.getBean(beanName, EntreprisePhoto.class);
                break;
            case "utilisateur":
                photoStrategy = beanFactory.getBean(beanName, UtilisateurPhoto.class);
                break;
            default:
                throw new InvalidOperationException("context inconnue lors de l'enregistrement de la photo", ErrorCodes.UNKNOWN_CONTEXT);

        }
    }
}
