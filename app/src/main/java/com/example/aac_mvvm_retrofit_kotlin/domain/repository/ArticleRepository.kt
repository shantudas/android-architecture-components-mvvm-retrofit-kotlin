package com.example.aac_mvvm_retrofit_kotlin.domain.model.repository

import com.example.aac_mvvm_retrofit_kotlin.data.remote.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.util.Resource

interface ArticleRepository {
    suspend fun getArticles(
        country: String,
        token: String
    ): Resource<ArticleResponse>
}