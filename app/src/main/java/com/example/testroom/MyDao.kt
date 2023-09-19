package com.example.testroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert
    fun insertWord (wordEntity: WordEntity)
    @Delete
    fun deleteWord (wordEntity: WordEntity)
    @Query("SELECT*FROM words")
    fun getWords() : Flow<List<WordEntity>>
}