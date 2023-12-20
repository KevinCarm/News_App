package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.Response
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.utils.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class NewsViewModel : ViewModel() {
    private val newsApi = RetrofitHelper
        .getInstance()
        ?.create(NewsApi::class.java)

    val articles: MutableLiveData<Response> = MutableLiveData()

    fun getArticles(
        searchWord: String,
        pageSize: Int,
        apiKey: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = newsApi?.getEverything(searchWord, pageSize, apiKey)
            if(response?.isSuccessful == true) {
                withContext(Dispatchers.Main) {
                    articles.postValue(response.body())
                }
            }
        }
    }
}