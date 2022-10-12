package com.example.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.db.model.InfoCharacter
import com.example.rickandmorty.db.viewModel.CharacterDetailViewModel
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding: FragmentCharacterDetailBinding by autoCleared()
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let {
            characterDetailViewModel.start(it)
        }
        setupObservers()
    }

    private fun setupObservers() {
        characterDetailViewModel.characterId.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(character: InfoCharacter) {
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}