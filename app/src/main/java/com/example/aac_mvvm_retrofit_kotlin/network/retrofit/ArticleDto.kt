package com.example.aac_mvvm_retrofit_kotlin.network.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleDto(
    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null,

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
)

