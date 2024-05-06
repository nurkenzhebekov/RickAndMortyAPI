package com.example.rickandmortyapi.ui.fragment.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.Character
import com.example.rickandmortyapi.databinding.FragmentCharacterBinding
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharacterClickListener {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() =_binding!!
    private val characterAdapter by lazy {
        CharacterAdapter(this)
    }
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setCharacter()
    }

    private fun setCharacter() {
        viewModel.getCharacter()
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
                        binding.progressBar.visibility = View.GONE
                        characterAdapter.submitList(data.data)
                    }
                }
            }
    }

    private fun setupRecycler() = with(binding.rvCharacters) {
        adapter = characterAdapter
        layoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCharacterClick(character: Character) {
        val bundle = Bundle().apply {
            putInt("characterId", character.id)
        }
        findNavController().navigate(R.id.action_characterFragment_to_characterDetailFragment, bundle)
    }
}