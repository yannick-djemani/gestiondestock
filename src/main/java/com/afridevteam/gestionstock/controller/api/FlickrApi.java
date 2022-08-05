package com.afridevteam.gestionstock.controller.api;

import java.io.InputStream;

public interface FlickrApi {
    String savePhoto(InputStream photo, String title);
}
