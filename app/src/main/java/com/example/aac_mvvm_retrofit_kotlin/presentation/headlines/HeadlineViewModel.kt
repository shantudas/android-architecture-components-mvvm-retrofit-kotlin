package com.example.aac_mvvm_retrofit_kotlin.presentation.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.data.remote.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.domain.model.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.util.Resource
import com.example.aac_mvvm_retrofit_kotlin.util.Constants
import com.example.aac_mvvm_retrofit_kotlin.util.NetworkHelper
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


    val articles: MutableLiveData<List<Article>> = MutableLiveData()

    // error message
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String>
        get() = _errorMessage

    // response
    private val _articleResponse =
        MutableStateFlow<Resource<ArticleResponse>>(Resource.Empty())
    val articleResponse: StateFlow<Resource<ArticleResponse>>
        get() = _articleResponse


    private var feedResponse: ArticleResponse? = null

    init {
        fetchArticles(Constants.CountryCode);
//        viewModelScope.launch {
//            val results = repository.getArticles("us", token)
//            articles.value = results
//        }
    }

    private fun fetchArticles(countryCode: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                _articleResponse.value = Resource.Loading()
                when (val response = repository.getArticles(countryCode, token)) {
                    is Resource.Success -> {
                        _articleResponse.value = handleArticleResponse(response)
                    }
                    is Resource.Error -> {
                        _articleResponse.value =
                            Resource.Error(
                                response.message ?: "Error"
                            )
                    }
                    else -> {}
                }

            }
        } else {
            _errorMessage.value = "No internet available"
        }
    }

    private fun handleArticleResponse(response: Resource<ArticleResponse>): Resource<ArticleResponse> {
        response.data?.let { resultResponse ->
            return Resource.Success(feedResponse ?: resultResponse)
        }
        return Resource.Error("No data found")
    }
}