package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.network.response.ArticleResponse
import com.example.aac_mvvm_retrofit_kotlin.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.state.NetworkState
import com.example.aac_mvvm_retrofit_kotlin.util.Constants
import com.example.aac_mvvm_retrofit_kotlin.util.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
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
        MutableStateFlow<NetworkState<ArticleResponse>>(NetworkState.Empty())
    val articleResponse: StateFlow<NetworkState<ArticleResponse>>
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
                _articleResponse.value = NetworkState.Loading()
                when (val response = repository.getArticles(countryCode, token)) {
                    is NetworkState.Success -> {
                        _articleResponse.value = handleArticleResponse(response)
                    }
                    is NetworkState.Error -> {
                        _articleResponse.value =
                            NetworkState.Error(
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

    private fun handleArticleResponse(response: NetworkState<ArticleResponse>): NetworkState<ArticleResponse> {
        response.data?.let { resultResponse ->
            return NetworkState.Success(feedResponse ?: resultResponse)
        }
        return NetworkState.Error("No data found")
    }
}