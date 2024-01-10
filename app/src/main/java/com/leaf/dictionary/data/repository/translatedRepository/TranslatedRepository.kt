package com.leaf.dictionary.data.repository.translatedRepository

import com.leaf.dictionary.data.models.DictionaryType

interface TranslatedRepository {
    suspend fun word(type: DictionaryType, oppositeWord: String): String
}