package com.example.aac_mvvm_retrofit_kotlin.network.retrofit

import com.example.aac_mvvm_retrofit_kotlin.network.response.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    /*
    * get top headlines through rest api
    *
    * @param    country             country we want get news from
    * @param    apiKey              auth token of https://newsapi.org/
    * @return   ArticleResponse     response from network which contains status,list and count
    *
    * this is suspend function, that means it can call inside coroutine and another suspend function
    * */
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): ArticleResponse

    /*
    * search from everything
    *
    * @param    q                   query for search
    * @param    apiKey              auth token of https://newsapi.org/
    * @return   ArticleResponse     response from network which contains status,list and count
    *
    * this is suspend function, that means it can call inside coroutine and another suspend function
    * */
    @GET("everything")
    suspend fun search(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): ArticleResponse
}