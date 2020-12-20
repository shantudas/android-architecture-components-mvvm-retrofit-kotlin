package com.example.aac_mvvm_retrofit_kotlin.repository

import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.RetrofitService

class ArticleRepository_Impl(
    private val retrofitService: RetrofitService,
    private val mapper: ArticleDtoMapper

) : ArticleRepository {
    override suspend fun getArticles(country: String, token: String): List<Article> {
        return mapper.mapToDomainList(retrofitService.getHeadlines(country, token).articles)
    }
}