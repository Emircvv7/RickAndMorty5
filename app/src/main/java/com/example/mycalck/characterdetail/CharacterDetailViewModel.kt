package com.example.mycalck.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.mycalck.character.CharacterZ
import com.example.mycalck.data.CharacterRepository
import com.example.mycalck.utils.Resource
import kotlinx.coroutines.launch

class CharacterDetailViewModel (
    private val repository: CharacterRepository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()
    val character: LiveData<Resource<CharacterZ>> = _id.switchMap { id ->
        liveData {
            emit(Resource.Loading())
            emitSource(repository.getCharacter(id))
        }
    }

    fun loadCharacter(id: Int) {
        _id.value = id
    }
}
