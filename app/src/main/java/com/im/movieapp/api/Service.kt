package com.im.movieapp.api

import com.im.movieapp.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("discover/movie/")
    fun discoverMovies(
        @Query("api_key") apiKey: String = "3bd22dd25744fd40c22891c6fc37b0bf",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("year") year: Int = 2020,
        @Query("language") language: String = "pt-BR"
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = "3bd22dd25744fd40c22891c6fc37b0bf",
        @Query("language") language: String = "pt-BR"
    ): Call<Movie>
}