package com.afridevteam.gestionstock.service.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface PhotoStrategy<T> {
    T UploadImage(Long id, InputStream photo, String titre) throws FlickrException;
}
