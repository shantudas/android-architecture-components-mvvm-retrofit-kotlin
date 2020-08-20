package com.example.aac_mvvm_retrofit_kotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "headlines")
class HeadlineCacheEntity(

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,

    @ColumnInfo(name = "url")
    var url: String
) {

}