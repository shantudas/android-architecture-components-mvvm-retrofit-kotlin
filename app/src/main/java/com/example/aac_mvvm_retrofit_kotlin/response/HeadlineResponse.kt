package com.example.aac_mvvm_retrofit_kotlin.response

import com.example.aac_mvvm_retrofit_kotlin.retrofit.HeadlineDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeadlineResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: List<HeadlineDto>
)