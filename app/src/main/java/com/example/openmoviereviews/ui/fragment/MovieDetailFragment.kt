package com.example.openmoviereviews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bindSrcUrl
import com.example.openmoviereviews.base.BaseFragment
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*

private const val MOVIE_ITEM = "MOVIE_ITEM"

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

  private lateinit var binding: FragmentMovieDetailBinding
  private lateinit var movieItem: MovieItem

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let { movieItem = it.getParcelable(MOVIE_ITEM)!! }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = FragmentMovieDetailBinding.inflate(LayoutInflater.from(activity), container, false)
    binding.movieItem = movieItem
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setBookData()
  }

  private fun setBookData() {
    ivPoster.bindSrcUrl(movieItem.multimedia.src)
    ivBackdrop.bindSrcUrl(movieItem.multimedia.src)
    if (movieItem.headline.length > 10) {
      tvBookTitleValue.isSelected = true
    }
  }

  companion object {
    @JvmStatic
    fun newInstance(movieItem: MovieItem) =
        MovieDetailFragment().apply {
          arguments = Bundle().apply { putParcelable(MOVIE_ITEM, movieItem) }
        }
  }
}
