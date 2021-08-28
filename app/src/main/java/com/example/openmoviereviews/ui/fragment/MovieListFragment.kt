package com.example.openmoviereviews.ui.fragment

import TOAST
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.openmoviereviews.api.listener.OpenDetailFragmentListener
import com.example.openmoviereviews.base.BaseFragment
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.databinding.FragmentMovieListBinding
import com.example.openmoviereviews.ui.fragment.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {

  private val viewModel: MovieViewModel by viewModels()
  private var listener: OpenDetailFragmentListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.movieItemClicked.observeEvent(this, ::navigateToDetailScreen)
    viewModel.showErrorMessage.observeEvent(this, this::showErrorMessage)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    val binding = FragmentMovieListBinding.inflate(LayoutInflater.from(activity), container, false)
    binding.viewModel = viewModel
    binding.tLayout.ivFilter.setOnClickListener { viewModel.sortMovieWithRating() }
    return binding.root
  }

  private fun navigateToDetailScreen(movieItem: MovieItem) =
      listener?.openMovieDetailFragment(movieItem)

  private fun showErrorMessage(msg: String?) = TOAST(msg)

  override fun onAttach(context: Context) {
    super.onAttach(context)
    try {
      if (context is OpenDetailFragmentListener) listener = context
    } catch (castException: ClassCastException) {
      /** The activity does not implement the listener. */
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    listener = null
  }

  companion object {
    @JvmStatic fun newInstance() = MovieListFragment()
  }
}
