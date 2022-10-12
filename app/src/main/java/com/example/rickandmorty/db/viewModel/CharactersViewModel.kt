package com.example.rickandmorty.db.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.db.model.InfoCharacter
import com.example.rickandmorty.db.repository.CharacterRepository
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters: LiveData<Resource<List<InfoCharacter>>> = repository.getCharacters()

}