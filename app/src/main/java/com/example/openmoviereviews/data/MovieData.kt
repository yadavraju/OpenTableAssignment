package com.example.openmoviereviews.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    @SerializedName("copyright") val copyright: String = "",
    @SerializedName("has_more") val hasMore: Boolean = false,
    @SerializedName("num_results") val numResults: Int = 0,
    @SerializedName("results") val movieItems: List<MovieItem> = listOf(),
    @SerializedName("status") val status: String = ""
) : Parcelable

@Parcelize
data class MovieItem(
    @SerializedName("byline") val byline: String = "",
    @SerializedName("critics_pick") val criticsPick: Int = 0,
    @SerializedName("date_updated") val dateUpdated: String = "",
    @SerializedName("display_title") val displayTitle: String = "",
    @SerializedName("headline") val headline: String = "",
    @SerializedName("link") val link: Link = Link(),
    @SerializedName("mpaa_rating") val mpaaRating: String = "",
    @SerializedName("multimedia") val multimedia: Multimedia = Multimedia(),
    @SerializedName("opening_date") val openingDate: String = "",
    @SerializedName("publication_date") val publicationDate: String = "",
    @SerializedName("summary_short") val summaryShort: String = ""
) : Parcelable

@Parcelize
data class Link(
    @SerializedName("suggested_link_text") val suggestedLinkText: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("url") val url: String = ""
) : Parcelable

@Parcelize
data class Multimedia(
    @SerializedName("height") val height: Int = 0,
    @SerializedName("src") val src: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("width") val width: Int = 0
) : Parcelable
