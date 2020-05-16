package com.example.aac_mvvm_retrofit_kotlin.model

data class Headline(
    var author: String,
    var title: String,
    var description: String,
    var imageUrl: String,
    var publishedAt: String
) {
    override fun toString(): String {
        return "Headline(author='$author', title='$title', description='$description', imageUrl='$imageUrl', publishedAt='$publishedAt')"
    }
}

