package com.somadey.convoyapp.model;

import android.util.Log;

import com.somadey.convoyapp.utils.Constants;
import com.somadey.convoyapp.utils.Constants.*;

import static com.somadey.convoyapp.utils.Constants.OFFER_PAGE_LIMIT;

public class OfferSearchOptions {
    @Override
    public String toString() {
        return "OfferSearchOptions{" +
                "offerSortOrder=" + offerSortOrder +
                ", offerSort=" + offerSort +
                ", limit=" + limit +
                '}';
    }

    private OfferSortOrder offerSortOrder;
    private OfferSort offerSort;
    private int limit = OFFER_PAGE_LIMIT;
    private static final String TAG = "OfferSearchOptions";

    public OfferSearchOptions(OfferSortOrder offerSortOrder, OfferSort offerSort, int limit) {
        this.offerSortOrder = offerSortOrder;
        this.offerSort = offerSort;
        this.limit = limit;
    }

    public int getOffset(int pageno){
        return  (pageno-1)*limit;
    }

    public OfferSortOrder getOfferSortOrder() {
        return offerSortOrder;
    }

    public void setOfferSortOrder(OfferSortOrder offerSortOrder) {
        this.offerSortOrder = offerSortOrder;
    }

    public OfferSort getOfferSort() {
        return offerSort;
    }

    public void setOfferSort(OfferSort offerSort) {
        this.offerSort = offerSort;
    }

    public int getLimit() {
        return limit;
    }

    public void log(int pageNo) {
        Log.d(TAG, this.toString());
        Log.d(TAG, "OfferSearchOptions{" +"pageNo=" + pageNo +'}');
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
