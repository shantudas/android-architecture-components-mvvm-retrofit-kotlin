package com.example.aac_mvvm_retrofit_kotlin.room

import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.example.aac_mvvm_retrofit_kotlin.util.DomainMapper
import javax.inject.Inject

class HeadlineEntityMapper
@Inject
constructor() : DomainMapper<HeadlineEntity, Article> {
    override fun mapToDomainModel(model: HeadlineEntity): Article {
        return Article(
            title = model.title,
            author = model.author,
            description = model.description,
            thumbnail = model.thumbnail,
            publishedAt = model.publishedAt,
            url = model.url
        )
    }

    override fun mapFromDomain(domainModel: Article): HeadlineEntity {
        return HeadlineEntity(
            title = domainModel.title,
            author = domainModel.author,
            description = domainModel.description,
            thumbnail = domainModel.thumbnail,
            publishedAt = domainModel.publishedAt,
            url = domainModel.url
        )
    }

    fun mapFromEntityList(entities: List<HeadlineEntity>): List<Article>{
        return entities.map { mapToDomainModel(it) }
    }
}