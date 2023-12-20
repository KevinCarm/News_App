package com.example.newsapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getEverything(
        @Query("q") searchWord: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Response<com.example.newsapp.data.Response>
}