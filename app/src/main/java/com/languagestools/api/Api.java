package com.languagestools.api;

import com.languagestools.api.languageTool.LanguageToolResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("task")
    Call<LanguageToolResponse> languagesTools(@Query("page") int pageIndex);
}
