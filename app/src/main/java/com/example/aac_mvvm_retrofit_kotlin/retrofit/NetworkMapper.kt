package com.example.aac_mvvm_retrofit_kotlin.retrofit

import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import com.example.aac_mvvm_retrofit_kotlin.utilities.EntityMapper
import javax.inject.Inject

/**
 * This class is responsible for mapping the network to domain object
 * and domain to network
 * */
class NetworkMapper

@Inject
constructor() : EntityMapper<HeadlineNetworkEntity, Headline> {

    override fun mapFromEntity(entity: HeadlineNetworkEntity): Headline {
        return Headline(
            title = entity.title,
            author = entity.author,
            description = entity.description,
            thumbnail = entity.urlToImage,
            publishedAt = entity.publishedAt,
            url = entity.url
        )
    }

    override fun mapToEntity(domainModel: Headline): HeadlineNetworkEntity {
        return HeadlineNetworkEntity(
            title = domainModel.title,
            author = domainModel.author,
            description = domainModel.description,
            urlToImage = domainModel.thumbnail,
            publishedAt = domainModel.publishedAt,
            url = domainModel.url
        )
    }


    /**
     * this method does not belongs to interface implementation
     *
     * @params HeadlineNetworkEntity
     * @return list of Headline
     * */
    fun mapFromEntityList(entities: List<HeadlineNetworkEntity>): List<Headline> {
        return entities.map { mapFromEntity(it) }
    }
}