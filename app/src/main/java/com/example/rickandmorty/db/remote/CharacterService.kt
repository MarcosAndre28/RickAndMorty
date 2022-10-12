package com.example.rickandmorty.db.remote

import com.example.rickandmorty.db.model.CharacterList
import com.example.rickandmorty.db.model.InfoCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<InfoCharacter>

}