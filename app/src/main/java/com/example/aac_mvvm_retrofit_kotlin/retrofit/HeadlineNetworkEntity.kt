package com.example.aac_mvvm_retrofit_kotlin.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HeadlineNetworkEntity(
    @SerializedName("author")
    @Expose
    var author: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String,

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String,

    @SerializedName("url")
    @Expose
    var url: String
)

