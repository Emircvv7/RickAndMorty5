package com.example.mycalck.data

import androidx.lifecycle.LiveData
import com.example.mycalck.character.CharacterResponse
import com.example.mycalck.character.CharacterZ
import com.example.mycalck.data.base.BaseRepository
import com.example.mycalck.utils.Resource
import retrofit2.Retrofit

class CharacterRepository(
    private val retrofit: Retrofit,
) : BaseRepository() {
    private val api = retrofit.create(RickAndMortyApiService::class.java)

    fun getCharacters(): LiveData<Resource<CharacterResponse>> {
        return doRequest { Resource.Success(api.getCharacters()) }
    }

    fun getCharacter(id: Int): LiveData<Resource<CharacterZ>> {
        return doRequest { Resource.Success(api.getCharacter(id)) }
    }
}
