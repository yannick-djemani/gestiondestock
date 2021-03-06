package com.afridevteam.gestionstock.exception;

public enum ErrorCodes {
    ARTICLE_NOT_FOUND(1000),
    ARTICLE_NOT_VALID(1001),
    ARTICLE_ALREADY_EXIST(1002),
    ARTICLE_ALREADY_IN_USE(1003),

    CATEGORY_NOT_FOUND(2000),
    CATEGORY_NOT_VALID(2001),
    CATEGORY_NOT_ALREADY_EXIST(2002),
    CATEGORY_ALREADY_IN_USE(2003),

    CLIENT_NOT_FOUND(3000),
    CLIENT_NOT_VALID(3001),
    CLIENT_ALREADY_EXIST(3002),
    CLIENT_ALREADY_IN_USE(3003),

    FOURNISSEUR_NOT_FOUND(4000),
    FOURNISSEUR_NOT_VALID(4001),
    FOURNISSEUR_ALREADY_EXIST(4002),
    FOURNISSEUR_ALREADY_IN_USE(4003),

    ENTREPRISE_NOT_FOUND(5000),
    ENTREPRISE_NOT_VALID(5001),
    ENTREPRISE_NOT_ALREADY_EXIST(5002),
    ENTREPRISE_ALREADY_IN_USE(1003),

    UTILISATEUR_NOT_FOUND(6000),
    UTILISATEUR_NOT_VALID(6001),
    UTILISATEUR_ALREADY_EXIST(6002),
    UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID(6003),

    BAD_CREDENTIALS(7000),

    VENTE_NOT_FOUND(8000),
    VENTE_NOT_VALID(8001),
    VENTE_ALREADY_IN_USE(8002),
    VENTE_ALREADY_EXIST(8003),

    UPDATE_PHOTO_EXCEPTION(9000),

    MVT_STK_NOT_FOUND(10000),
    MVT_STK_NOT_VALID(10001),

    COMMANDE_CLIENT_NOT_FOUND(11000),
    COMMANDE_CLIENT_NOT_VALID(11001),
    COMMANDE_CLIENT_ALREADY_EXIST(11002),
    COMMANDE_CLIENT_ALREADY_IN_USE(11003),

    COMMANDE_FOURNISSEUR_NOT_FOUND(12000),
    COMMANDE_FOURNISSEUR_NOT_VALID(12001),
    COMMANDE_FOURNISSEUR_ALREADY_IN_USE(12002),
    COMMANDE_FOURNISSEUR_NOT_MODIFIED(12003),

    LIGNE_COMMANDE_CLIENT_NOT_FOUND(13000),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(14000),
    LIGNE_VENTE_NOT_FOUND(15000),

    UNKNOWN_CONTEXT(16000);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
