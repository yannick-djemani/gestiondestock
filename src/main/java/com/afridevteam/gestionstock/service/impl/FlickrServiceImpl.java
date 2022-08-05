package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.service.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FlickrServiceImpl implements FlickrService {
    private final BeanFactory beanFactory;

    public FlickrServiceImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    @SneakyThrows
    public String savePhoto(InputStream photo, String title) {
        Flickr flickr = (Flickr) beanFactory.getBean("getFlickr2");
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getThumbnailUrl();
    }


}
