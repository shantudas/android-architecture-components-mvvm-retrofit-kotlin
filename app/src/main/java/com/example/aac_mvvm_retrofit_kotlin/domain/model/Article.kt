package com.example.aac_mvvm_retrofit_kotlin.domain.model

data class Article(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnail: String? = null,
    var publishedAt: String? = null,
    var url: String? = null
)

