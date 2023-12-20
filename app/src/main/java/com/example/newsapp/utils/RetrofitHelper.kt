package com.example.newsapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        private const val baseUrl = "https://newsapi.org/v2/"
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit? {
            if(retrofit != null) {
                return retrofit
            }
            retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}