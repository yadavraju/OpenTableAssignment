package com.example.openmoviereviews.injection

import android.app.Application
import android.content.Context
import com.example.openmoviereviews.injection.ForApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

  @Provides
  @Singleton
  @ForApplication
  fun provideContext(application: Application): Context {
    return application
  }
}
