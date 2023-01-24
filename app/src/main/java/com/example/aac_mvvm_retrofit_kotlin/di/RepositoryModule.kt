package com.example.aac_mvvm_retrofit_kotlin.di

import com.example.aac_mvvm_retrofit_kotlin.data.mapper.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.data.remote.NewsApi
import com.example.aac_mvvm_retrofit_kotlin.domain.model.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.data.repository.ArticleRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(
        newsApi: NewsApi,
        articleDtoMapper: ArticleDtoMapper
    ): ArticleRepository {
        return ArticleRepository_Impl(
            newsApi = newsApi,
            mapper = articleDtoMapper
        )
    }
}