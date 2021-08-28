package com.example.openmoviereviews.ui.fragment.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.openmoviereviews.api.repository.MovieRepository
import com.example.openmoviereviews.base.BaseViewModel
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.adapter.CommonAdapter
import com.example.openmoviereviews.ui.factory.AppFactory
import com.example.openmoviereviews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val TAG: String = "MainViewModel"

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val repository: MovieRepository,
    private val adapter: CommonAdapter,
    private val appFactory: AppFactory
) : BaseViewModel() {

  private val movieAdapterObservableField: ObservableField<CommonAdapter> = ObservableField()
  private val loadingObservableField: ObservableField<Boolean> = ObservableField()

  val showErrorMessage: SingleLiveEvent<String?> by lazy { SingleLiveEvent() }
  val movieItemClicked: SingleLiveEvent<MovieItem> by lazy { SingleLiveEvent() }
  private var movieItems: List<MovieItem> = listOf()

  init {
    fetchMovieData()
  }

  fun fetchMovieData() {
    viewModelScope.launch {
      loadingObservableField.set(true)
      repository
          .getMoviesData()
          .catch { e -> handleException(TAG, e, loadingObservableField) }
          .collect {
            movieItems = it.movieItems
            setMovieAdapterData(it.movieItems)
            loadingObservableField.set(false)
          }
    }
  }

  fun setMovieAdapterData(characterImages: List<MovieItem>) {
    val viewModels = characterImages.map { appFactory.createMovieAdapter(it, ::itemClicked) }
    adapter.setDataBoundAdapter(viewModels)
    movieAdapterObservableField.set(adapter)
  }

  fun sortMovieWithRating() {
    setMovieAdapterData(
        movieItems.asSequence().filter { movieItem -> movieItem.mpaaRating.isNotEmpty() }.toList())
  }

  private fun itemClicked(movieItem: MovieItem) {
    movieItemClicked.value = movieItem
  }

  override fun showExceptionMessage(message: String?) {
    showErrorMessage.value = message
  }

  fun getCharacterAdapter(): ObservableField<CommonAdapter> = movieAdapterObservableField

  fun isLoading(): ObservableField<Boolean> = loadingObservableField
}
