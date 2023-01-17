package com.example.aac_mvvm_retrofit_kotlin.network.response

import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.ArticleDto
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: MutableList<ArticleDto>
)