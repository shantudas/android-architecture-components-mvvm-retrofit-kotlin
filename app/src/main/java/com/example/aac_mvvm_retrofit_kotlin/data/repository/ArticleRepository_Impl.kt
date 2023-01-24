package com.example.aac_mvvm_retrofit_kotlin.data.repository

import com.example.aac_mvvm_retrofit_kotlin.data.mapper.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.data.remote.ArticleApi
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.domain.model.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.util.Resource

class ArticleRepository_Impl(
    private val articleApi: ArticleApi,
    private val mapper: ArticleDtoMapper

) : ArticleRepository {
    override suspend fun getArticles(
        country: String,
        token: String
    ): Resource<List<Article>> {
        return try {
            val response = articleApi.getArticles(country, token)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                Resource.Success(
                    mapper.mapToDomainList(responseBody.articles)
                )
            } else {
                Resource.Error("An error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("Error occurred ${e.localizedMessage}")
        }
    }
}