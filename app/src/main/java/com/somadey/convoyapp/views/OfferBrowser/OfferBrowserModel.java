package com.somadey.convoyapp.views.OfferBrowser;

import android.util.Log;


import com.somadey.convoyapp.model.Offer;
import com.somadey.convoyapp.model.OfferSearchOptions;
import com.somadey.convoyapp.network.ApiClient;
import com.somadey.convoyapp.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import com.somadey.convoyapp.utils.Constants;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserContract.Model;

public class OfferBrowserModel implements Model {
    private final String TAG = "OfferBrowserModel";

    public OfferSearchOptions getOfferSearchOptions() {
        return offerSearchOptions;
    }

    private OfferSearchOptions offerSearchOptions = new OfferSearchOptions(
            Constants.OfferSortOrder.DESC, Constants.OfferSort.PICKUPDATE,Constants.OFFER_PAGE_LIMIT);

    @Override
    public void getOfferList(final OnFinishedListener onFinishedListener, int pageNo ) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Offer>> call = apiService.getOffers(
                offerSearchOptions.getOfferSort().name(),
                offerSearchOptions.getLimit(),
                offerSearchOptions.getOffset(pageNo),
                offerSearchOptions.getOfferSortOrder().name());

        offerSearchOptions.log(pageNo);

        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                List<Offer> offers = response.body();
                if (offers != null)
                    Log.d(TAG, "Number of Offers received: " + offers.size());
                else
                    offers = new ArrayList<Offer>();
                onFinishedListener.onFinished(offers);
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void updateResultsOrder(Constants.OfferSort offerSort) {
        offerSearchOptions.setOfferSort(offerSort);
    }

}
