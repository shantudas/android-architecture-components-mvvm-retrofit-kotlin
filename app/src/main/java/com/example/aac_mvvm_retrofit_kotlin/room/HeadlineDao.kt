package com.example.aac_mvvm_retrofit_kotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeadlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: HeadlineEntity): Long

    @Query("SELECT * FROM headlines")
    suspend fun get(): List<HeadlineEntity>
}