package com.example.animeapp.ui.home

import android.view.ViewGroup
import com.example.animeapp.ui.base.BaseRecyclerViewAdapter

class AnimeRecyclerViewAdapter(private val onClick:(HomeUiData)->Unit) : BaseRecyclerViewAdapter<HomeUiData,AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder.createForm(parent)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val card = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(card)
        }
    }
}