package com.example.openmoviereviews.ui.factory

import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.adapter.MovieAdapter
import javax.inject.Inject

class AppFactory @Inject constructor() {

  fun createMovieAdapter(movieItem: MovieItem, listener: (mi: MovieItem) -> Unit): MovieAdapter {
    return MovieAdapter(movieItem, listener)
  }
}
