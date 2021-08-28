package com.example.openmoviereviews.ui.fragment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.openmoviereviews.TestCoroutineRule
import com.example.openmoviereviews.api.repository.MovieRepository
import com.example.openmoviereviews.data.MovieData
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.adapter.CommonAdapter
import com.example.openmoviereviews.ui.factory.AppFactory
import com.example.openmoviereviews.util.SOMETHING_WENT_WRONG
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
  @get:Rule val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

  @get:Rule val testCoroutineRule = TestCoroutineRule()

  @Mock private lateinit var repository: MovieRepository

  @Mock private lateinit var adapter: CommonAdapter

  @Mock private lateinit var appFactory: AppFactory

  @Mock private lateinit var bookDataFlow: Flow<MovieData>

  private lateinit var testObject: MovieViewModel

  @Before
  fun setUp() {
    testObject = MovieViewModel(repository, adapter, appFactory)
  }

  @Test
  fun fetchBookData() {
    testCoroutineRule.runBlockingTest {
      Mockito.doReturn(bookDataFlow).`when`(repository).getMoviesData()
      testObject.fetchMovieData()
      assertEquals(true, testObject.isLoading().get())
    }
  }

  @Test
  fun setCharacterAdapterData() {
    val characterList = listOf(MovieItem(), MovieItem())
    testObject.setMovieAdapterData(characterList)
    verify(adapter).setDataBoundAdapter(any())
    assertNotNull(testObject.getCharacterAdapter())
  }

  @Test
  fun sortMovieWithRating() {
    val characterList = listOf(MovieItem(mpaaRating = ""), MovieItem(mpaaRating = "R"))
    testObject.setMovieAdapterData(characterList)
    verify(adapter).setDataBoundAdapter(any())
    assertNotNull(testObject.getCharacterAdapter())
  }

  @Test
  fun showExceptionMessage() {
    testObject.showExceptionMessage(SOMETHING_WENT_WRONG)
    assertNotNull(testObject.showErrorMessage)
  }
}
