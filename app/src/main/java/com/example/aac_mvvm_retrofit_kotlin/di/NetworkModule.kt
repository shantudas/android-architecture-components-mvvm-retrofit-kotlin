package com.example.aac_mvvm_retrofit_kotlin.di

import com.example.aac_mvvm_retrofit_kotlin.data.mapper.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.data.remote.NewsApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideArticleMapper(): ArticleDtoMapper {
        return ArticleDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(): NewsApi {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return "494d8b052f544a4e9848574a6c4930bc"
    }
}