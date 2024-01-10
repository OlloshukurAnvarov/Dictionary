package com.leaf.dictionary.data.repository.mainRepository

import com.leaf.dictionary.data.models.Dictionary

interface MainRepository {

    suspend fun addDictionaries(dictionaries: List<Dictionary>)

    suspend fun enWords(): List<String>

    suspend fun uzWords(): List<String>

    suspend fun searchEnWords(query: String): List<String>

    suspend fun searchUzWords(query: String): List<String>

}