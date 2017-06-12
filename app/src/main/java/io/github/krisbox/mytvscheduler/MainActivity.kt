package io.github.krisbox.mytvscheduler

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.github.krisbox.mytvscheduler.fragments.Searching.SearchResultsFragment

/**
 * Description: Main Activity for the App
 * @author Kris Box
 * Time: 19:08
 * Date: 01/01/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
*/

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /**
     * When the Activity is created create the Toolbar,
     * Drawer
     * NavigationView and SearchView
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        search()
    }

    /**
     * Search View, listens for TextSubmit and searches for data when happens
     */
    fun search(){
        val searchView = findViewById(R.id.searchView) as SearchView

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                callSearch(query)
                return true
            }
            override fun onQueryTextChange(newText:String):Boolean {
                return true
            }
            fun callSearch(query:String) {

                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val searchResults = SearchResultsFragment(query, this@MainActivity)
                fragmentTransaction.replace(R.id.search_fragment_placeholder, searchResults, "SEARCH_FRAGMENT")
                fragmentTransaction.commit()
            }

        })
    }

    /**
     * Navigation Drawer Stuff TODO
     */
    override fun onBackPressed() {

        val fragMan = fragmentManager
        val searchFrag = fragmentManager.findFragmentByTag("SEARCH_FRAGMENT")
        if (searchFrag != null && searchFrag.isVisible){
            val fragmentTransaction = fragMan.beginTransaction()
            fragmentTransaction.remove(searchFrag)
            fragmentTransaction.commit()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here TODO
        when (item.itemId) {
            R.id.nav_home -> {
            }
            R.id.nav_watch -> {
            }
            R.id.nav_settings -> {
            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
