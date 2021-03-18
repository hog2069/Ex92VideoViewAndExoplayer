package com.hog2020.ex92videoviewandexoplayer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;

public interface RetrofitService {

    @GET("Videos/videoData.json")
    Call<String> getVideoDatas();



}
