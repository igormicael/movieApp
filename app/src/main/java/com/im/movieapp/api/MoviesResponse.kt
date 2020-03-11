package com.im.movieapp.api

import com.google.gson.annotations.SerializedName
import com.im.movieapp.model.Movie

data class MoviesResponse (
    @SerializedName("results") val movies: List<Movie>
)