package org.wikiedufoundation.wikiedudashboard.ui.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.view.ProfileFragment

/**
 * Wikimedia homepage activity
 * ***/
class HomeActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by inject()

    private var context: Context? = null
    private var myDashboardFragment: MyDashboardFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main)
        ButterKnife.bind(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_explore)
        setSupportActionBar(toolbar)
        context = this
        myDashboardFragment = MyDashboardFragment()
        val navView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_explore -> {
                    replaceFragment(ExploreFragment())
                    true
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(myDashboardFragment)
                    true
                }
                R.id.navigation_training -> {
                    sharedPrefs.userName?.let { sharedName -> replaceFragment(ProfileFragment.newInstance(sharedName, false)) }
                    true
                }
                else -> false
            }
        }
        addFragment(MyDashboardFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_explore, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val searchView = item?.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.isIconified = false

        val txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setTextColor(Color.BLACK)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                myDashboardFragment?.updateSearchQuery(query)
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                item.collapseActionView()
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                myDashboardFragment?.updateSearchQuery(query)
                return false
            }
        })
        return true
    }

    private fun addFragment(fragment: Fragment?) {
        fragment?.let {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().apply {
                add(R.id.home_container, it)
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                commit()
            }
        }
    }

    /**
     * Use [clearStack] to clear fragment back stack
     * ***/
    fun clearStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            val first = manager.getBackStackEntryAt(1)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        fragment?.let {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.home_container, fragment)
                commit()
            }
        }
        if (fragment is MyDashboardFragment) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }
}
