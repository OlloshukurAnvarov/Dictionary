package com.leaf.dictionary.ui.fragment.translatedFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leaf.dictionary.data.models.DictionaryType
import com.leaf.dictionary.data.repository.translatedRepository.TranslatedRepository
import com.leaf.dictionary.data.repository.translatedRepository.TranslatedRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslatedViewModel @Inject constructor(private val repository: TranslatedRepository) : ViewModel() {
    private val _word = MutableLiveData<String>()
    val word: LiveData<String> = _word

    fun oppositeWord(type: DictionaryType, oppositeWord: String){
        viewModelScope.launch {
           _word.value = repository.word(type, oppositeWord)
        }
    }
}