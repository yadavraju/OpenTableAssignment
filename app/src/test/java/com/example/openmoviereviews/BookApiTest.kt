package com.example.openmoviereviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.openmoviereviews.api.MovieApi
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class MovieApiTest {

  @Rule @JvmField val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var service: MovieApi

  private lateinit var mockWebServer: MockWebServer

  @Before
  fun createService() {
    mockWebServer = MockWebServer()
    service =
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieApi::class.java)
  }

  @After
  fun stopService() {
    mockWebServer.shutdown()
  }

  @Test
  fun getBookDataTest() = runBlocking {
    enqueueResponse("mock_movie_data_response.json")
    val response = service.getMoviesData()

    assertNotNull(response)
    assertEquals(response.status, "OK")
    assertEquals(response.numResults, 20)
  }

  private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = inputStream.source().buffer()
    val mockResponse = MockResponse()
    for ((key, value) in headers) {
      mockResponse.addHeader(key, value)
    }
    mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
  }
}
