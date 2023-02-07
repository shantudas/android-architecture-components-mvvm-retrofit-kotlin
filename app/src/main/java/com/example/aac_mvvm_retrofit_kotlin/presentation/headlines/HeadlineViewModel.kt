package com.example.aac_mvvm_retrofit_kotlin.presentation.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_mvvm_retrofit_kotlin.domain.model.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.util.Constants
import com.example.aac_mvvm_retrofit_kotlin.util.NetworkHelper
import com.example.aac_mvvm_retrofit_kotlin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val repository: ArticleRepository,
    @Named("auth_token") private val token: String,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val TAG: String = "HeadlineViewModel"

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<HeadlineUiState>(HeadlineUiState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<HeadlineUiState> = _uiState

    init {
        getArticles(Constants.CountryCode)
    }

    fun refreshArticles(){
        getArticles(Constants.CountryCode)
    }

    private fun getArticles(countryCode: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                when (val result = repository.getArticles(countryCode, token)) {
                    is Resource.Success -> {
                        _uiState.value= HeadlineUiState.Success(articles= result.data!!)
                    }
                    is Resource.Error -> {
                        _uiState.value=HeadlineUiState.Error("Some error occurred ")
                    }
                    else -> {
                        Unit
                    }
                }
            }
        } else {
            _uiState.value=HeadlineUiState.Error(message = "No internet available")
        }
    }
}