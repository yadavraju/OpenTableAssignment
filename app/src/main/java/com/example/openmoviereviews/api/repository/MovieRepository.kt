package com.example.openmoviereviews.api.repository

import com.example.openmoviereviews.api.MovieApi
import com.example.openmoviereviews.data.MovieData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi) {

  fun getMoviesData(): Flow<MovieData> {
    return object : NetworkBoundRepository<MovieData>() {
      override suspend fun fetchFromRemote(): MovieData = api.getMoviesData()
    }.asFlow()
  }
}
