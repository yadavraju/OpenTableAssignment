package com.example.openmoviereviews.ui.adapter

enum class ViewType {
  MOVIE_ITEM;

  val id: Int
    get() = ordinal
}
