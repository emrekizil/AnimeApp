package com.example.animeapp.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.animeapp.databinding.FragmentDetailBinding
import com.example.animeapp.utility.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding :FragmentDetailBinding

    private val args:DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAnime()
        observeUiState()
    }
    private fun getAnime(){
        viewModel.getAnimeWithId(args.id)
    }
    private fun observeUiState(){

        viewModel.animeDetailUiState.observe(viewLifecycleOwner){
            when(it){
                is DetailUiState.Error->{
                    Toast.makeText(requireContext(),getString(it.message),Toast.LENGTH_LONG).show()
                }
                is DetailUiState.Loading->{
                    Toast.makeText(requireContext(),"Loading Content",Toast.LENGTH_SHORT).show()
                }
                is DetailUiState.Success->{
                    println(it.data)
                    handleSuccessUiState(it.data)
                }

            }
        }
    }

    private fun handleSuccessUiState(data: DetailUiData) {
        binding.animeImage.loadImage(data.imageUrl)
        binding.name.text=data.name
        binding.popularityRank.text=data.popularityRank
        binding.description.text=data.description
    }


}