package com.example.aac_mvvm_retrofit_kotlin.repository

import com.example.aac_mvvm_retrofit_kotlin.model.Article

interface ArticleRepository {
    suspend fun getArticles(country: String, token: String): List<Article>
}