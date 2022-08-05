package com.afridevteam.gestionstock.controller;

import com.afridevteam.gestionstock.controller.api.FlickrApi;
import com.afridevteam.gestionstock.service.FlickrService;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class FlickrController implements FlickrApi {
    private final FlickrService flickrService;

    public FlickrController(FlickrService flickrService) {
        this.flickrService = flickrService;
    }

    @Override
    public String savePhoto(InputStream photo, String title) {
        return flickrService.savePhoto(photo, title);
    }
}
