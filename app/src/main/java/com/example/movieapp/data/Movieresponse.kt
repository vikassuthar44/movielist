package com.example.movieapp.data

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val results: List<MovieData>
) {
    data class MovieData(
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String
    )
}