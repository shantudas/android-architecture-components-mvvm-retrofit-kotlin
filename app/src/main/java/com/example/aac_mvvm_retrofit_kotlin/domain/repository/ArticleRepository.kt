package com.example.aac_mvvm_retrofit_kotlin.domain.model.repository

import com.example.aac_mvvm_retrofit_kotlin.data.remote.dto.ArticleDto
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.util.Resource

interface ArticleRepository {
    suspend fun getArticles(
        country: String,
        token: String
    ): Resource<List<Article>>
}