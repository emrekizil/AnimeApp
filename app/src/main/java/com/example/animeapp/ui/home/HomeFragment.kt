package com.example.animeapp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentHomeBinding
import com.example.animeapp.utility.observeTextChanges
import com.example.animeapp.utility.okWith
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val adapter = AnimeRecyclerViewAdapter()

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            animeListRecyclerView.adapter=adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchTextChanges()
        observeUiState()
    }

    private fun observeUiState(){
        viewModel.animeHomeUiState.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Error -> {
                    Toast.makeText(requireContext(), getString(it.message), Toast.LENGTH_LONG)
                        .show()
                }
                HomeUiState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is HomeUiState.Success -> {
                    handleSuccessUiState(it.data)
                }
            }
        }
    }

    private fun handleSuccessUiState(data: List<HomeUiData>) {
        adapter.updateItems(data)
    }

    private fun observeSearchTextChanges() {
        binding.searchEditText.observeTextChanges()
            .filter { it okWith MINIMUM_SEARCH_LENGTH }
            .debounce(SEARCH_DEBOUNCE_TIME_IN_MILLISECONDS)
            .distinctUntilChanged()
            .onEach {
                viewModel.getAnimeWithCategories(it)
            }.launchIn(lifecycleScope)
    }

    companion object {
        private const val MINIMUM_SEARCH_LENGTH = 1
        private const val SEARCH_DEBOUNCE_TIME_IN_MILLISECONDS = 300L
    }





}