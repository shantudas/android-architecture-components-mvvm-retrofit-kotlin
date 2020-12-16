package com.example.aac_mvvm_retrofit_kotlin.utilities

/*
* this interface represents to convert an entity to domain model and vice verse
*
* T : interfaces parameter
* T could be 2 type, 1:network type(retrofit) as DTO, 2:local database type(room) as Entity
*
* DomainModel : application level domain class or business domain model
* all model class inside model folder should be passed as DomainModel
* */
interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel  //from entity to domain model

    fun mapFromDomain(domainModel: DomainModel): T   //from domain model to entity
}