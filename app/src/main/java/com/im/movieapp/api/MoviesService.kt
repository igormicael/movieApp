package com.im.movieapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie/")
    fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("year") year: Int = 2020,
        @Query("language") language: String = "pt-BR"
    ): Call<MoviesResponse>
}