package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.repository.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Named

@ExperimentalCoroutinesApi
class HeadlineViewModel
@ViewModelInject
constructor(
    private val repository: ArticleRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {
    private val TAG: String = "AppDebug"
    val articles: MutableLiveData<List<Article>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val results = repository.getArticles("us", token)
            articles.value = results
        }
    }
}