package com.example.animeapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.animeapp.databinding.LayoutAnimeBinding
import com.example.animeapp.ui.home.HomeUiData
import com.example.animeapp.utility.loadImage


class AnimeUiComponent @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet?=null,
    defStyleAttr:Int=0
) : FrameLayout(context,attributesSet,defStyleAttr){
    private val binding = LayoutAnimeBinding.inflate(LayoutInflater.from(context),this,false)

    init {

        addView(binding.root)
    }

    fun setAnimeData(homeUiData: HomeUiData){
        binding.name.text = homeUiData.name
        binding.animeImage.loadImage(homeUiData.imageUrl)
    }
}