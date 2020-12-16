package com.example.aac_mvvm_retrofit_kotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headlines")
class HeadlineEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "author")
    var author: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String? = null,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null
)