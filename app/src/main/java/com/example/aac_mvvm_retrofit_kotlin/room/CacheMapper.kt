package com.example.aac_mvvm_retrofit_kotlin.room

import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import com.example.aac_mvvm_retrofit_kotlin.utilities.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<HeadlineCacheEntity, Headline> {
    override fun mapFromEntity(entity: HeadlineCacheEntity): Headline {
        return Headline(
            title = entity.title,
            author = entity.author,
            description = entity.description,
            thumbnail = entity.thumbnail,
            publishedAt = entity.publishedAt,
            url = entity.url
        )
    }

    override fun mapToEntity(domainModel: Headline): HeadlineCacheEntity {
        return HeadlineCacheEntity(
            title = domainModel.title,
            author = domainModel.author,
            description = domainModel.description,
            thumbnail = domainModel.thumbnail,
            publishedAt = domainModel.publishedAt,
            url = domainModel.url
        )
    }

    fun mapFromEntityList(entities: List<HeadlineCacheEntity>): List<Headline>{
        return entities.map { mapFromEntity(it) }
    }
}