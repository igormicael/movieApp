package com.im.movieapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.im.movieapp.model.Movie

@Dao
interface Dao {

    @Insert
    fun save(movie: Movie)

    @Query(value = "Select * from movie where title IN (:title)")
    fun getByTitle(title: String): List<Movie>

}