package com.leaf.dictionary.data.repository.translatedRepository

import com.leaf.dictionary.data.AppDataBase
import com.leaf.dictionary.data.dao.DictDao
import com.leaf.dictionary.data.models.DictionaryType
import javax.inject.Inject

class TranslatedRepositoryImp @Inject constructor(private val dataBase: DictDao): TranslatedRepository {

    override suspend fun word(type: DictionaryType, oppositeWord: String): String {
        val en = dataBase.enWords()
        val uz = dataBase.uzWords()
        val word = when (type) {
            DictionaryType.EN_UZ -> uz[en.indexOf(oppositeWord)]
            DictionaryType.UZ_EN -> en[uz.indexOf(oppositeWord)]
        }
        return word
    }
}