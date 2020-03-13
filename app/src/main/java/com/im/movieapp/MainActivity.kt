package com.im.movieapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.im.movieapp.R.id.nav_fav
import com.im.movieapp.R.id.nav_home
import com.im.movieapp.lists.ListAdapter
import com.im.movieapp.lists.OnListListener
import com.im.movieapp.model.Movie
import com.im.movieapp.persistence.Dao
import com.im.movieapp.persistence.Db
import com.im.movieapp.persistence.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.drawer_header.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnListListener {

    var movies: ArrayList<Movie> = arrayListOf();
    var shownMovies: List<Movie> = listOf();

    private lateinit var dao: Dao
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle =
            ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)
        Repository.discoverMovies(
            ::onMovieSuccess,
            ::onMovieError
        )

        var database = Room.databaseBuilder(
                this,
                Db::class.java,
                "movies"
            )
            .allowMainThreadQueries()
            .build()

        dao = database.dao()
        auth = FirebaseAuth.getInstance()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            nav_home -> {
                drawer_title.text = "Filmes"
                showAllMovies();
            }
            nav_fav -> {
                drawer_title.text = "Favoritos"
                showFavoritesMovies();
            }
        }

        var movieList = movies_list
        movieList.adapter = ListAdapter(shownMovies, this)
        movieList.layoutManager = GridLayoutManager(this, 2)

        drawer_layout.closeDrawer(GravityCompat.START)

        user.text = auth.currentUser?.email

        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            auth.signOut();
            startActivity(intent)
            finish()
        }
        return true
    }

    private fun onMovieSuccess(movies: List<Movie>) {
        this.movies = movies as ArrayList<Movie>
    }

    private fun onMovieError() {
        Toast.makeText(this, "Deu ruim!!!", Toast.LENGTH_LONG)
            .show()
    }

    fun showAllMovies() {
        shownMovies = movies
    }

    fun showFavoritesMovies() {

        var teste = dao.getByUser(auth.currentUser?.email.toString())

        Log.v("main", teste.toString());

        shownMovies = dao.getByUser(auth.currentUser?.email.toString())
    }

    override fun onClick(position: Int) {
        var movie = shownMovies[position]

        val intent = Intent(this, MovieDetailActivity::class.java)
        val b = Bundle()
        b.putInt("id", movie.id)
        intent.putExtra("bundle", b)

        startActivity(intent)
    }

}
