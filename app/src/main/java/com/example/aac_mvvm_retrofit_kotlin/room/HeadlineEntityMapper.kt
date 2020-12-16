package com.example.aac_mvvm_retrofit_kotlin.room

import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import com.example.aac_mvvm_retrofit_kotlin.utilities.DomainMapper
import javax.inject.Inject

class HeadlineEntityMapper
@Inject
constructor() : DomainMapper<HeadlineEntity, Headline> {
    override fun mapToDomainModel(model: HeadlineEntity): Headline {
        return Headline(
            title = model.title,
            author = model.author,
            description = model.description,
            thumbnail = model.thumbnail,
            publishedAt = model.publishedAt,
            url = model.url
        )
    }

    override fun mapFromDomain(domainModel: Headline): HeadlineEntity {
        return HeadlineEntity(
            title = domainModel.title,
            author = domainModel.author,
            description = domainModel.description,
            thumbnail = domainModel.thumbnail,
            publishedAt = domainModel.publishedAt,
            url = domainModel.url
        )
    }

    fun mapFromEntityList(entities: List<HeadlineEntity>): List<Headline>{
        return entities.map { mapToDomainModel(it) }
    }
}