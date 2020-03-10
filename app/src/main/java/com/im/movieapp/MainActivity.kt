package com.im.movieapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.im.movieapp.R.id.nav_fav
import com.im.movieapp.R.id.nav_home
import com.im.movieapp.lists.ListAdapter
import com.im.movieapp.lists.OnListListener
import com.im.movieapp.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.movie_list.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnListListener {

    var movies: ArrayList<Movie> = arrayListOf();
    var shownMovies: List<Movie> = listOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle =
            ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)

        initMoviesList()
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
        movieList.layoutManager = LinearLayoutManager(this)

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun initMoviesList() {
        var movie1 = Movie("E o vento levou 1", "http://index.imgur.com/DvpvklR.png", "descrição longaa", false);
        var movie2 = Movie("E o vento levou 2", "http://index.imgur.com/DvpvklR.png", "descrição longaa", false);
        var movie3 = Movie("E o vento não levou 1", "http://index.imgur.com/DvpvklR.png", "descrição longaaaa", true);
        var movie4 = Movie("E o vento não levou 2", "http://index.imgur.com/DvpvklR.png", "descrição longaaaa", true);
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
    }

    fun showAllMovies() {
        shownMovies = movies.filter { i -> !i.fav }
    }

    fun showFavoritesMovies() {
        shownMovies = movies.filter { i -> i.fav }
    }

    override fun onClick(position: Int) {
        TODO("Not yet implemented")
    }

}
