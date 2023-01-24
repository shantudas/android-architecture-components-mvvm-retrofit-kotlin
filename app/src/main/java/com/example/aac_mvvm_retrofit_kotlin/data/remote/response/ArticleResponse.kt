package com.example.aac_mvvm_retrofit_kotlin.data.remote.response

import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: MutableList<Article>
)