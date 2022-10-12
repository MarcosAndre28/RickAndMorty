package com.example.rickandmorty.db.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.rickandmorty.db.model.InfoCharacter
import com.example.rickandmorty.db.repository.CharacterRepository
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val id = MutableLiveData<Int>()
    private val character = id.switchMap { id ->
        repository.getCharacter(id)
    }

    val characterId: LiveData<Resource<InfoCharacter>> = character

    fun start(characterId: Int) {
        id.value = characterId
    }


}