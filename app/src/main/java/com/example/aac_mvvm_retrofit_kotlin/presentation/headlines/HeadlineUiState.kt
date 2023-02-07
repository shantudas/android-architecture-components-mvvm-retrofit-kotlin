package com.example.aac_mvvm_retrofit_kotlin.presentation.headlines

import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article

sealed class HeadlineUiState {
    data class Success(val articles: List<Article>) : HeadlineUiState()
    object Loading : HeadlineUiState()
    data class Error(val message: String) : HeadlineUiState()
}