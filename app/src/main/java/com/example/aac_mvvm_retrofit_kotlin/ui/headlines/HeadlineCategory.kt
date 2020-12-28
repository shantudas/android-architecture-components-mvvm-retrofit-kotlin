package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import com.example.aac_mvvm_retrofit_kotlin.ui.headlines.HeadlineCategory.*

enum class HeadlineCategory(val value: String) {
    ENTERTAINMENT("entertainment"),
    BUSINESS("business"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
}

fun getAllHeadlineCategories(): List<HeadlineCategory> {
    return listOf(ENTERTAINMENT, BUSINESS, GENERAL, HEALTH, SCIENCE, SPORTS, TECHNOLOGY)
}

fun getHeadlineCategory(value: String): HeadlineCategory? {
    val map = HeadlineCategory.values().associateBy(HeadlineCategory::value)
    return map[value]
}