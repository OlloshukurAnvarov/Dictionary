package com.leaf.dictionary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.leaf.dictionary.data.models.Dictionary

@Dao
interface DictDao {
    @Insert
    suspend fun addDictionaries(dictionaries: List<Dictionary>)

    @Query("SELECT word_en FROM dictionaries")
    suspend fun enWords() : List<String>

    @Query("SELECT word_uz FROM dictionaries")
    suspend fun uzWords() : List<String>

    @Query("SELECT word_en FROM dictionaries WHERE word_en LIKE :query || '%'")
    suspend fun searchEnWords(query : String) : List<String>

    @Query("SELECT word_uz FROM dictionaries WHERE word_uz LIKE :query || '%'")
    suspend fun searchUzWords(query : String) : List<String>

}