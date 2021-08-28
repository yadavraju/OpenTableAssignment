package com.example.openmoviereviews.ui.adapter

import android.view.View
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.viewholder.MovieViewHolder
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MovieAdapterTest {

  private lateinit var testObject: MovieAdapter

  @Before
  fun setUp() {

    testObject = MovieAdapter(MovieItem(), ::itemClicked)
  }

  private fun itemClicked(movieItem: MovieItem) {}

  @Test
  fun createItemViewHolder() {
    val view = mock<View> {}
    assertNotNull(testObject.createItemViewHolder(view))
  }

  @Test
  fun bindItemViewHolder() {
    val holder = mock<MovieViewHolder> {}
    testObject.bindItemViewHolder(holder)
    verify(holder).bind(any(), any())
  }

  @Test
  fun getViewType() {
    assertEquals(ViewType.MOVIE_ITEM, testObject.viewType)
  }
}
