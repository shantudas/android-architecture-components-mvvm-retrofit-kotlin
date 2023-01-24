package com.example.aac_mvvm_retrofit_kotlin.data.repository

import com.example.aac_mvvm_retrofit_kotlin.data.remote.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.data.mapper.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.data.remote.NewsApi
import com.example.aac_mvvm_retrofit_kotlin.domain.model.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.util.Resource

class ArticleRepository_Impl(
    private val newsApi: NewsApi,
    private val mapper: ArticleDtoMapper

) : ArticleRepository {
    override suspend fun getArticles(
        country: String,
        token: String
    ):/* List<Article> {
        return mapper.mapToDomainList(retrofitService.getHeadlines(country, token).articles)
    }*/

            Resource<ArticleResponse> {
        return try {
            val response = newsApi.getArticles(country, token)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error("An error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("Error occurred ${e.localizedMessage}")
        }
    }
}