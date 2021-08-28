package com.example.openmoviereviews.ui.viewholder

import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import bindSrcUrl
import com.example.openmoviereviews.R
import com.example.openmoviereviews.data.MovieItem
import kotlinx.android.synthetic.main.movie_view_layout.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(movieItem: MovieItem, listener: (mi: MovieItem) -> Unit) {

    itemView.tv_movie_title.text = movieItem.displayTitle
    if (movieItem.displayTitle.length > 30) itemView.tv_movie_title.isSelected = true
    if (movieItem.mpaaRating.isNotEmpty()) {
      itemView.llRating.visibility = View.VISIBLE
      itemView.tvVoteAverage.text = movieItem.mpaaRating
    } else {
      itemView.llRating.visibility = View.GONE
    }
    itemView.iv_movie.bindSrcUrl(
        movieItem.multimedia.src,
        placeholder = getDrawable(itemView.context, R.drawable.small_placeholder))

    itemView.setOnClickListener { listener(movieItem) }
  }
}
