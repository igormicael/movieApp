package com.im.movieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.im.movieapp.model.Movie
import com.im.movieapp.persistence.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var intent: Intent = this.intent
        var bundle = intent.getBundleExtra("bundle")

        var id = bundle!!.getInt("id")

        Repository.getMovie(
            id,
            ::onMoviesSuccess,
            ::onMovieError
        )
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


        fav.setOnClickListener {
            Toast.makeText(this, getString(R.string.add_to_fav), Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun onMovieError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG)
            .show()
    }
}
