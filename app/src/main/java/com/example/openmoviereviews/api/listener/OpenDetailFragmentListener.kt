package com.example.openmoviereviews.api.listener

import com.example.openmoviereviews.data.MovieItem

interface OpenDetailFragmentListener {
  fun openMovieDetailFragment(movieItem: MovieItem)
}
