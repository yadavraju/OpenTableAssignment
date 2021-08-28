package com.example.openmoviereviews.api

import com.example.openmoviereviews.BuildConfig
import com.example.openmoviereviews.data.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

  @GET("reviews/picks.json")
  suspend fun getMoviesData(
      @Query("api-key") apiKey: String = BuildConfig.API_KEY,
      @Query("offset") offset: Int = 0
  ): MovieData
}
