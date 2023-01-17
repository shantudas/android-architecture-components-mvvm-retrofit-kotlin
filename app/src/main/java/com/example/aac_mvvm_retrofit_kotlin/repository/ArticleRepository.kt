package com.example.aac_mvvm_retrofit_kotlin.repository

import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.network.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.state.NetworkState

interface ArticleRepository {
    suspend fun getArticles(country: String, token: String): NetworkState<ArticleResponse>
}