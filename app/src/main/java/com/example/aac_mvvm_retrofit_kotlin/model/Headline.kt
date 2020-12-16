package com.example.aac_mvvm_retrofit_kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class Headline(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnail: String? = null,
    var publishedAt: String? = null,
    var url: String? = null
) : Parcelable

