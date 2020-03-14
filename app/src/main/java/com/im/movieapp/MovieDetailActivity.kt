package com.im.movieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.im.movieapp.model.Movie
import com.im.movieapp.persistence.Dao
import com.im.movieapp.persistence.Db
import com.im.movieapp.persistence.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var dao: Dao
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val intent: Intent = this.intent
        val bundle = intent.getBundleExtra("bundle")

        val id = bundle!!.getInt("id")

        Repository.getMovie(
            id,
            ::onMoviesSuccess,
            ::onMovieError
        )

        val database = Room.databaseBuilder(
                this,
                Db::class.java,
                "movies"
            )
            .allowMainThreadQueries()
            .build()

        dao = database.dao()
        auth = FirebaseAuth.getInstance()

    }

    private fun onMoviesSuccess(movie: Movie) {
        movie_title.text = movie.title
        movie_overview.text = movie.overview

        Picasso.get()
            .load(getString(R.string.imgPrefix) + movie.poster_path)
            .fit()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.mipmap.ic_launcher_round)
            .into(movie_image)

        val exists = dao.getById(movie.id)

        if (exists != null && exists.id != null) {
            fav.text = getString(R.string.faved)
        }

        fav.setOnClickListener {
            if (exists == null) {

                movie.user = auth.currentUser!!.email.toString()
                dao.save(movie)
                Toast.makeText(this, getString(R.string.add_to_fav), Toast.LENGTH_LONG).show()
                fav.text = getString(R.string.faved)
                fav.setOnClickListener {}
            } else {
                Toast.makeText(this, getString(R.string.ja_eh_fav), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onMovieError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG)
            .show()
    }
}
