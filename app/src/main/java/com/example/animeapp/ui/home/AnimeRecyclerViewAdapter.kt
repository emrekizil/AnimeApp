package com.example.animeapp.ui.home

import android.view.ViewGroup
import com.example.animeapp.ui.base.BaseRecyclerViewAdapter

class AnimeRecyclerViewAdapter : BaseRecyclerViewAdapter<HomeUiData,AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder.createForm(parent)
    }
}