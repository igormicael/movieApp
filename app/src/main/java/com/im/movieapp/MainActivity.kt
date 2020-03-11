package com.im.movieapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.im.movieapp.R.id.nav_fav
import com.im.movieapp.R.id.nav_home
import com.im.movieapp.api.MoviesService
import com.im.movieapp.lists.ListAdapter
import com.im.movieapp.lists.OnListListener
import com.im.movieapp.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnListListener {

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
        movieList.layoutManager = GridLayoutManager(this, 2)

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun initMoviesList() {

        /*var retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build();

        var service = retrofit.create(MoviesService::class.java);

        val discoverMovies = service.discoverMovies("3bd22dd25744fd40c22891c6fc37b0bf")

        var request = discoverMovies.execute().body();

        var searchedMovies = request?.movies

        movies = searchedMovies as ArrayList<Movie>

        */

        var movie1 = Movie(
            1,
            "E o vento levou 1",
            "nmlpEQuemz1OMEAVLP5RA8pZUML.jpg",
            "descrição longaa",
            false
        );
        var movie2 = Movie(
            2,
            "E o vento levou 2",
            "Kt9iFdTu5TbAm7tNfc876mrWU.jpg",
            "descrição longaa",
            false
        );
        var movie21 = Movie(
            3,
            "E o vento levou 2",
            "nmlpEQuemz1OMEAVLP5RA8pZUML.jpg",
            "descrição longaa",
            false
        );
        var movie22 = Movie(
            4,
            "E o vento levou 2",
            "5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            "descrição longaa",
            false
        );
        var movie23 = Movie(
            5,
            "E o vento levou 2",
            "nmlpEQuemz1OMEAVLP5RA8pZUML.jpg",
            "descrição longaa",
            false
        );
        var movie24 = Movie(
            6,
            "E o vento levou 2",
            "5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            "descrição longaa",
            false
        );
        var movie3 = Movie(
            7,
            "E o vento não levou 1",
            "5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            "descrição longaaaa",
            true
        );
        var movie4 = Movie(
            8,
            "E o vento não levou 2",
            "5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            "descrição longaaaa",
            true
        );
        movies.add(movie1);
        movies.add(movie2);movies.add(movie21);movies.add(movie22);movies.add(movie23);movies.add(
            movie24
        );
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
