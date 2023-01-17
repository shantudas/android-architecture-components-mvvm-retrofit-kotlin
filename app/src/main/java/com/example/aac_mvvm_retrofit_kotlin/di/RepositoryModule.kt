package com.example.aac_mvvm_retrofit_kotlin.di

import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.RetrofitService
import com.example.aac_mvvm_retrofit_kotlin.repository.ArticleRepository
import com.example.aac_mvvm_retrofit_kotlin.repository.ArticleRepository_Impl
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
        retrofitService: RetrofitService,
        articleDtoMapper: ArticleDtoMapper
    ): ArticleRepository{
        return ArticleRepository_Impl(
            retrofitService = retrofitService,
            mapper = articleDtoMapper
        )
    }
}