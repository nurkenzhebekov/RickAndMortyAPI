package com.example.rickandmortyapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rickandmortyapi.databinding.FragmentCharacterBinding
import com.example.rickandmortyapi.ui.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() =_binding!!
    private var adapter = CharacterAdapter(arrayListOf())
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setCharacter()
    }

    private fun setCharacter() {
        viewModel.getCharacter()
            .observe(viewLifecycleOwner) { characterList ->
                adapter.list.clear()
                adapter.list.addAll(characterList.results)
                adapter.notifyDataSetChanged()
            }
    }

    private fun setAdapter() {
        binding.rvCharacters.adapter = adapter
    }
}