package com.ziterz.marlo.User.API;

import com.ziterz.marlo.User.Model.Laundries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ziterz on 7/12/2017.
 */

public interface ApiInterface {

    @GET("laundry")
    Call<Laundries> getLaundry(@Query("api_token") String apiKey);
//
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
//
}
