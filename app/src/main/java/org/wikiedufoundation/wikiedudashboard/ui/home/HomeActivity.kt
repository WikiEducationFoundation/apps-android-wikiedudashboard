package org.wikiedufoundation.wikiedudashboard.ui.home

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.view.ProfileFragment
import android.graphics.Color
import android.widget.EditText

class HomeActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null
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
        sharedPrefs = SharedPrefs(this)
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
                } R.id.navigation_training -> {
                replaceFragment(ProfileFragment.newInstance(sharedPrefs!!.userName!!, false))
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
//        if (item!!.itemId==R.id.action_search) {
            val searchView = item!!.actionView as SearchView
            searchView.queryHint = "Search"
            searchView.isIconified = false

        val txtSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setTextColor(Color.BLACK)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    myDashboardFragment!!.updateSearchQuery(query)
                    if (!searchView.isIconified) {
                        searchView.isIconified = true
                    }
                    item.collapseActionView()
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    myDashboardFragment!!.updateSearchQuery(query)
                    return false
                }
            })
            return true
//        }else {
//
//        }
//        return true
    }



    private fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.home_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction.commit()
        }
    }

    fun clearStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            val first = manager.getBackStackEntryAt(1)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, fragment)
            fragmentTransaction.commit()
        }
        if (fragment is MyDashboardFragment) {
            supportActionBar!!.show()
        } else {
            supportActionBar!!.hide()
        }
    }
}
