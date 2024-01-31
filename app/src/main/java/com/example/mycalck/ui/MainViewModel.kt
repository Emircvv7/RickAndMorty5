package com.example.mycalck.ui

import androidx.lifecycle.*
import com.example.mycalck.character.CharacterResponse
import com.example.mycalck.data.CharacterRepository
import com.example.mycalck.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: CharacterRepository
) : ViewModel() {
    private val _characters = MutableLiveData<Resource<CharacterResponse>>()
    val characters: LiveData<Resource<CharacterResponse>> get() = _characters

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val result = repository.getCharacters()
            result.observeForever { resource ->
                _characters.postValue(resource)
            }
        }
    }
}
