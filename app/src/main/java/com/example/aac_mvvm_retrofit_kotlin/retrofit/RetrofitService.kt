package com.example.aac_mvvm_retrofit_kotlin.retrofit

import com.example.aac_mvvm_retrofit_kotlin.response.HeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    /*
    * get top headlines through rest api
    *
    * @return HeadlineNetworkEntity list of headline articles entity
    * this is suspend function, that means it can call inside coroutine and another suspend function
    * */
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") key: String
    )
}