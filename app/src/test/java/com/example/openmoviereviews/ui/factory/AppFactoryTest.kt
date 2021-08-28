package com.example.openmoviereviews.ui.factory

import com.example.openmoviereviews.data.MovieItem
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppFactoryTest {

  lateinit var testObject: AppFactory

  @Before
  fun setUp() {
    testObject = AppFactory()
  }

  @Test
  fun createGiphyAdapter() {
    assertNotNull(testObject.createMovieAdapter(MovieItem(), ::itemClicked))
  }

  private fun itemClicked(movieItem: MovieItem) {}
}
