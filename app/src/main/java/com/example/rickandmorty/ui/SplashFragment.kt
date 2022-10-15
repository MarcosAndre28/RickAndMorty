package com.example.rickandmorty.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSpashBinding
import com.example.rickandmorty.utils.StatusColor.changeStatusBarColor
import com.example.rickandmorty.utils.autoCleared


class SplashFragment : Fragment() {

    private var binding: FragmentSpashBinding by autoCleared()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.back_ground
            ), false
        )
        charactersFragmentStart()
    }

    private fun charactersFragmentStart() {
        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(R.id.action_spashFragment_to_charactersFragment) },
            5000
        )
    }

}