package com.example.aac_mvvm_retrofit_kotlin.retrofit

import retrofit2.http.GET

interface Retrofit {

    /*
    * get top headlines through rest api
    *
    * @return HeadlineNetworkEntity list of headline articles entity
    * this is suspend function, that means it can call inside coroutine and another suspend function
    * */
    @GET("top-headlines")
    suspend fun getHeadlines(): List<HeadlineNetworkEntity>
}