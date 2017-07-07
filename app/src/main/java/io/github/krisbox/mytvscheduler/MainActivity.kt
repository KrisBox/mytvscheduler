package io.github.krisbox.mytvscheduler

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.github.krisbox.mytvscheduler.fragments.Discover.DiscoverFragment
import io.github.krisbox.mytvscheduler.fragments.Other.SettingsFragment
import io.github.krisbox.mytvscheduler.fragments.Program.WatchlistFragment
import io.github.krisbox.mytvscheduler.fragments.Searching.SearchResultsFragment
import java.io.File


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

        // Allow for the searching in toolbar
        search()

        // Load the discover fragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val discover = DiscoverFragment(this)
        fragmentTransaction.replace(R.id.search_fragment_placeholder, discover, "DISCOVER_FRAGMENT")
        fragmentTransaction.addToBackStack("DISCOVER_FRAGMENT")
        fragmentTransaction.commit()

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
                fragmentTransaction.addToBackStack("SEARCH_FRAGMENT")
                fragmentTransaction.commit()
            }

        })
    }

    override fun onBackPressed() {

        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here TODO
        when (item.itemId) {
            R.id.nav_home -> {
                // Load the discover fragment
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val discover = DiscoverFragment(this)
                fragmentTransaction.replace(R.id.search_fragment_placeholder, discover, "DISCOVER_FRAGMENT")
                fragmentTransaction.addToBackStack("DISCOVER_FRAGMENT")
                fragmentTransaction.commit()
            }
            R.id.nav_watch -> {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val watchlistResults = WatchlistFragment(this@MainActivity)
                fragmentTransaction.replace(R.id.search_fragment_placeholder, watchlistResults, "WATCHLIST_FRAGMENT")
                fragmentTransaction.addToBackStack("WATCHLIST_FRAGMENT")
                fragmentTransaction.commit()
            }
            R.id.nav_settings -> {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val settingsFragment = SettingsFragment(this@MainActivity)
                fragmentTransaction.replace(R.id.search_fragment_placeholder, settingsFragment, "SETTINGS_FRAGMENT")
                fragmentTransaction.addToBackStack("SETTINGS_FRAGMENT")
                fragmentTransaction.commit()
            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * The following is set up to delete the cache when the app is backed out of.
     */
    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            println ("Deleting all cache")
            trimCache(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun trimCache(context: Context) {
        try {
            val dir = context.cacheDir
            if (dir != null && dir.isDirectory) {
                deleteDir(dir)
            }
        } catch (e: Exception) {}

    }

    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }

        // The directory is now empty so delete it
        return dir!!.delete()
    }
}
