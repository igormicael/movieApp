package com.im.movieapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.im.movieapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun dao(): Dao
}