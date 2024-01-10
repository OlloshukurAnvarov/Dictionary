package com.leaf.dictionary.data.repository.mainRepository

import com.leaf.dictionary.data.dao.DictDao
import com.leaf.dictionary.data.models.Dictionary
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val dataBase: DictDao) : MainRepository {

    override suspend fun addDictionaries(dictionaries: List<Dictionary>) {
        if (dataBase.enWords().isEmpty())
            dataBase.addDictionaries(dictionaries)
    }

    override suspend fun enWords() = dataBase.enWords()

    override suspend fun uzWords() = dataBase.uzWords()

    override suspend fun searchEnWords(query: String) = dataBase.searchEnWords(query)

    override suspend fun searchUzWords(query: String) = dataBase.searchUzWords(query)

}