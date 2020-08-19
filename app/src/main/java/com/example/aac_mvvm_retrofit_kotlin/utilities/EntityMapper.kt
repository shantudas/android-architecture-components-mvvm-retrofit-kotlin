package com.example.aac_mvvm_retrofit_kotlin.utilities

/*
* this interface represents to convert an entity to domain model and vice verse
*
* Entity : interfaces parameter
* Entity could be 2 type, 1:network type(retrofit), 2:local database type(room)
*
* DomainModel : application level domain class
* all model class inside model folder should be passed as DomainModel
* */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel  //from entity to domain model

    fun mapToEntity(domainModel: DomainModel): Entity   //from domain model to entity
}