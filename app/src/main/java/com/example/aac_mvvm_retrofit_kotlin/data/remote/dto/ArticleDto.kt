package com.example.aac_mvvm_retrofit_kotlin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: List<ArticleListDto>
)