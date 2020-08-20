package com.example.aac_mvvm_retrofit_kotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeadlineCacheEntity::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun headlineDao(): HeadlineDao

    companion object {
        val DATABASE_NAME: String = "article_db"
    }
}