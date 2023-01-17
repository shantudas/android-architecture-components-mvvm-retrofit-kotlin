package com.example.aac_mvvm_retrofit_kotlin.repository

import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.network.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.RetrofitService
import com.example.aac_mvvm_retrofit_kotlin.state.NetworkState

class ArticleRepository_Impl(
    private val retrofitService: RetrofitService,
    private val mapper: ArticleDtoMapper

) : ArticleRepository {
    override suspend fun getArticles(
        country: String,
        token: String
    ):/* List<Article> {
        return mapper.mapToDomainList(retrofitService.getHeadlines(country, token).articles)
    }*/

            NetworkState<ArticleResponse> {
        return try {
            val response = retrofitService.getHeadlines(country, token)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                NetworkState.Success(result)
            } else {
                NetworkState.Error("An error occurred")
            }
        } catch (e: Exception) {
            NetworkState.Error("Error occurred ${e.localizedMessage}")
        }
    }
}