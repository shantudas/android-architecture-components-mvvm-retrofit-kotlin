package com.example.aac_mvvm_retrofit_kotlin.retrofit

import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import com.example.aac_mvvm_retrofit_kotlin.utilities.DomainMapper
import javax.inject.Inject

/**
 * This class is responsible for mapping the network to domain object
 * and domain to network
 * */
class HeadlineDtoMapper

@Inject
constructor() : DomainMapper<HeadlineDto, Headline> {

    override fun mapToDomainModel(model: HeadlineDto): Headline {
        return Headline(
            title = model.title,
            author = model.author,
            description = model.description,
            thumbnail = model.urlToImage,
            publishedAt = model.publishedAt,
            url = model.url
        )
    }

    override fun mapFromDomain(domainModel: Headline): HeadlineDto {
        return HeadlineDto(
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
    fun mapToDomainList(entities: List<HeadlineDto>): List<Headline> {
        return entities.map { mapToDomainModel(it) }
    }
}