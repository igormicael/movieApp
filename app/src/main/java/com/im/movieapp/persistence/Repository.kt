package com.im.movieapp.persistence

import com.im.movieapp.api.MovieResponse
import com.im.movieapp.api.Service
import com.im.movieapp.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private val service: Service

    init {

        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(Service::class.java)

    }

    fun discoverMovies(
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        service.discoverMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovie(
        movie_id: Int,
        onSuccess: (movie: Movie) -> Unit,
        onError: () -> Unit
    ) {


        service.getMovie(movie_id)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}