package com.example.openmoviereviews.ui.activity

import android.os.Bundle
import com.example.openmoviereviews.R
import com.example.openmoviereviews.api.listener.OpenDetailFragmentListener
import com.example.openmoviereviews.base.BaseActivity
import com.example.openmoviereviews.data.MovieItem
import com.example.openmoviereviews.ui.fragment.MovieDetailFragment
import com.example.openmoviereviews.ui.fragment.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), OpenDetailFragmentListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .add(R.id.fragmentContainer, MovieListFragment.newInstance())
          .commitAllowingStateLoss()
    }
  }

  override fun openMovieDetailFragment(movieItem: MovieItem) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragmentContainer, MovieDetailFragment.newInstance(movieItem))
        .addToBackStack(MovieDetailFragment::javaClass.name)
        .commitAllowingStateLoss()
  }
}
