package com.afridevteam.gestionstock.utils;

public interface Constants {
    String APP_ROOT = "gestiondestock/v1";
    String CATEGORIE_ENPOINT = APP_ROOT + "/categories";

    String CATEGORIE_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/categorie/id/{id}";

    String CATEGORIE_ENPOINT_BY_CODE = CATEGORIE_ENPOINT + "/categorie/code/{code}";

    String CLIENT_ENPOINT = APP_ROOT + "/clients";

    String CLIENT_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/client/id/{id}";


    String CLIENT_ENPOINT_BY_NAME = CATEGORIE_ENPOINT + "/client/nom/{name}";

    String ENTREPRISE_ENPOINT = APP_ROOT + "/entreprises";


    String ENTREPRISE_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/entreprise/id/{id}";


    String ENTREPRISE_ENPOINT_BY_NAME = CATEGORIE_ENPOINT + "/entreprise/nom/{name}";

}
