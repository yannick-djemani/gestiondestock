package com.afridevteam.gestionstock.utils;

public interface Constants {
    public static final String APP_ROOT = "gestiondestock/v1";

    public static final String VENTE_ENDPOINT = APP_ROOT + "/ventes";

    public static final String MvtStock_ENDPOINT = APP_ROOT + "/mvstocks";

    public static final String STOCK_REEL_ARTICLE = APP_ROOT + "/mvstocks";

    public static final String MVT_ARTICLE_ENDPOINT = APP_ROOT + "/mvstocks/idArticle";


    public static final String UTILISATEUR_ENDPOINT = APP_ROOT + "/utilisateurs";

    public static final String AUTHENTICATION_ENDPOINT = APP_ROOT + "/auth/authenticate";
    public static final String CATEGORIE_ENPOINT = APP_ROOT + "/categories";
    public static final String CATEGORIE_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/categorie/id/{id}";
    public static final String CATEGORIE_ENPOINT_BY_CODE = CATEGORIE_ENPOINT + "/categorie/code/{code}";

    //public static final String CLIENT_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/client/id/{id}";
    public static final String CLIENT_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/filter/client/{id}";

    // public static final String CLIENT_ENPOINT_BY_NAME = CATEGORIE_ENPOINT + "/client/nom/{name}";
    public static final String CLIENT_ENPOINT_BY_NAME = CATEGORIE_ENPOINT + "/filter/client/{name}";
    public static final String ENTREPRISE_ENPOINT_BY_ID = CATEGORIE_ENPOINT + "/entreprise/id/{id}";
    public static final String ENTREPRISE_ENPOINT_BY_NAME = CATEGORIE_ENPOINT + "/entreprise/nom/{name}";

    public static final String ARTICLE_ENPOINT = APP_ROOT + "/articles";
    public static final String CLIENT_ENPOINT = APP_ROOT + "/clients";

    public static final String ENTREPRISE_ENPOINT = APP_ROOT + "/entreprises";
    public static final String ARTICLE_ENDPOINT_BY_ID = APP_ROOT + "/{id}";
    public static final String ARTICLE_ENDPOINT_BY_CATEGORY_PAGE = APP_ROOT + "/filter/category/{idCategory}/page";
    public static final String ARTICLE_ENDPOINT_BY_CODE = APP_ROOT + "/filter/{code}";
    public static final String ARTICLE_ENDPOINT_BY_CATEGORY = APP_ROOT + "/filter/category/{idCategory}";

    public static final String ARTICLE_ENDPOINT_HISTORY_VENTE = APP_ROOT + "/historique/vente/{id}";
    public static final String ARTICLE_ENDPOINT_HISTORY_COMMANDE_CLIENT = APP_ROOT + "/historique/commandeclient/{id}";
    public static final String ARTICLE_ENDPOINT_HISTORY_COMMANDE_FOURNISSEUR = APP_ROOT + "/historique/commandefournisseur/{id}";

    public static final String VENTE_ENPOINT_BY_ID = APP_ROOT + "{id}";

    public static final String VENTE_ENPOINT_BY_code = APP_ROOT + "/fliter/{code}";


}
