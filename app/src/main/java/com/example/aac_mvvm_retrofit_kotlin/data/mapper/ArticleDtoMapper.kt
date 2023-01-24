package com.example.aac_mvvm_retrofit_kotlin.data.mapper

import com.example.aac_mvvm_retrofit_kotlin.data.remote.dto.ArticleDto
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.util.DomainMapper

/**
 * This class is responsible for mapping the network/database to domain object
 * and domain to network/database
 * */
class ArticleDtoMapper : DomainMapper<ArticleDto, Article> {

    //network to domain model
    override fun mapToDomainModel(model: ArticleDto): Article {
        return Article(
            title = model.title,
            author = model.author,
            description = model.description,
            thumbnail = model.urlToImage,
            publishedAt = model.publishedAt,
            url = model.url
        )
    }

    //domain model to network model
    override fun mapFromDomain(domainModel: Article): ArticleDto {
        return ArticleDto(
            title = domainModel.title,
            author = domainModel.author,
            description = domainModel.description,
            urlToImage = domainModel.thumbnail,
            publishedAt = domainModel.publishedAt,
            url = domainModel.url
        )
    }


    //network to domain list
    fun mapToDomainList(initial: List<ArticleDto>): List<Article> {
        return initial.map { mapToDomainModel(it) }
    }

    //domain to network list
    fun mapFromDomainList(initial: List<Article>): List<ArticleDto> {
        return initial.map {
            mapFromDomain(it)
        }
    }
}