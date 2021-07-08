package com.somadey.convoyapp.utils;

public class Constants {
    public static final String BASE_URL = "https://convoy-mock-api.herokuapp.com/";

    public enum OfferSort {
        PICKUPDATE  ("pickupDate"),
        DROPOFFDATE ("dropoffDate"),
        PRICE   ("price"),
        ORIGIN   ("origin"),
        DESTINATION   ("destination"),
        MILES   ("miles");

        private String sortType;
        private OfferSort(String sortType) {
            this.sortType = sortType;
        }

    }

    public enum OfferSortOrder {
        DESC  ("desc"),
        ASC   ("asc");

        private String sortOrderType;
        private OfferSortOrder(String sortOrderType) {
            this.sortOrderType = sortOrderType;
        }
    }

    public static final int OFFER_PAGE_LIMIT = 20;


}
