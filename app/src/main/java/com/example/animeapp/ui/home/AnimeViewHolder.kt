package com.example.animeapp.ui.home

import android.view.ViewGroup
import android.view.ViewParent
import com.example.animeapp.databinding.AdapterAnimeItemBinding
import com.example.animeapp.ui.base.BaseViewHolder
import com.example.animeapp.utility.inflateAdapterItem

class AnimeViewHolder(private val binding:AdapterAnimeItemBinding):BaseViewHolder<HomeUiData>(binding.root) {

    companion object{
        fun createForm(parent: ViewGroup) =
            AnimeViewHolder(parent.inflateAdapterItem(AdapterAnimeItemBinding::inflate))
    }
    override fun onBind(data: HomeUiData) {
        binding.animeComponent.setAnimeData(data)
    }

}