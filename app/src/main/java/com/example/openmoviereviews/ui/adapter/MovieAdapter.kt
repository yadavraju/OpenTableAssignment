package com.example.openmoviereviews.ui.adapter

import android.view.View
import com.example.openmoviereviews.R
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.adapter.ViewType.MOVIE_ITEM
import com.example.openmoviereviews.ui.viewholder.CharacterViewHolder

class MovieAdapter(
    private val movieItem: MovieItem,
    private val listener: (mi: MovieItem) -> Unit
) : BaseDataBoundAdapter<CharacterViewHolder>(R.layout.movie_view_layout) {

  override fun createItemViewHolder(view: View): CharacterViewHolder {
    return CharacterViewHolder(view)
  }

  override fun bindItemViewHolder(holder: CharacterViewHolder) {
    holder.bind(movieItem, listener)
  }

  override val viewType: ViewType
    get() = MOVIE_ITEM
}
