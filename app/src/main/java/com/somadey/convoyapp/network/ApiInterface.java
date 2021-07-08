package com.somadey.convoyapp.network;

import com.somadey.convoyapp.model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("offers")
    Call<List<Offer>> getOffers(@Query("sort") String sort,
                                       @Query("limit") int limit,
                                       @Query("offset") int offset,
                                       @Query("order") String order);
}
