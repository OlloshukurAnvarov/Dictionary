package com.leaf.dictionary.ui.fragment.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leaf.dictionary.data.models.DictionaryType
import com.leaf.dictionary.data.repository.mainRepository.MainRepository
import com.leaf.dictionary.data.repository.mainRepository.MainRepositoryImp
import com.leaf.dictionary.data.words
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _data = MutableLiveData<List<String>>()
    val data: LiveData<List<String>> = _data

    init {
        viewModelScope.launch {
            repository.addDictionaries(words())
        }
    }

    fun dictionaries(type: DictionaryType) {
        viewModelScope.launch {
            _data.postValue(
                when (type) {
                    DictionaryType.EN_UZ -> repository.enWords()
                    DictionaryType.UZ_EN -> repository.uzWords()
                }
            )
        }
    }

    fun searchedDictionaries(type: DictionaryType, query: String?) {
        viewModelScope.launch {
            if (!query.isNullOrEmpty()) {
                _data.value = when (type) {
                    DictionaryType.EN_UZ -> repository.searchEnWords(query)
                    DictionaryType.UZ_EN -> repository.searchUzWords(query)
                }
            }
        }
    }
}