package com.im.movieapp.action

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.im.movieapp.R
import com.im.movieapp.R.id.nav_fav
import com.im.movieapp.R.id.nav_home
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle =
            ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)

        // setSupportActionBar(toolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            nav_home -> {
                Log.v("teste", "nav_home")
            }
            nav_fav -> {
                Log.v("teste", "nav_fav")
            }
            else -> {
                Log.v("teste", "else")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
