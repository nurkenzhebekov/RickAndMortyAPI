package com.example.rickandmortyapi.ui.fragment.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapi.ui.extensions.setLifeStatusIndicator
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCharacter()
    }

    private fun setCharacter() {
        val characterId = arguments?.getInt("characterId") ?: -1
        if (characterId != -1) {
            viewModel.getCharacterById(characterId)
                .observe(viewLifecycleOwner) { data ->
                    when(data) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            setupCharacterData(data)
                        }
                    }
                }
        }
    }

    private fun setupCharacterData(data: Resource.Success<Character>) = with(binding) {
            progressBar.visibility = View.GONE
            imgCharacter.load(data.data.image)
            tvCharacterName.text = data.data.name
            tvCharacterLifeStatus.text = data.data.status
            tvCharacterRace.text = data.data.species
            tvGender.text = data.data.gender
            tvLastKnownLocation.text = data.data.location.name
            tvFirstSeenIn.text = data.data.episode.toString()

            imgLifeStatusIndicator.setLifeStatusIndicator(data.data.status)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}