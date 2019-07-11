package org.wikiedufoundation.wikiedudashboard.ui.home

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment

class HomeActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null
    private var context: Context? = null

//    private val mOnNavigationItemSelectedListener = { item ->
//        when (item.getItemId()) {
//            R.id.navigation_explore -> {
//                replaceFragment(ExploreFragment())
//            }
//            R.id.navigation_dashboard -> {
//                replaceFragment(RecentActivityFragment())
//            }
//            R.id.navigation_training -> {
//                replaceFragment(ProfileFragment())
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main)
        ButterKnife.bind(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_explore)
        setSupportActionBar(toolbar)
        context = this
        sharedPrefs = SharedPrefs(this)

        val navView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        navView.setOnNavigationItemSelectedListener(this.)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_explore -> {
                    replaceFragment(ExploreFragment())
                    true
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(MyDashboardFragment())
                    true
                } R.id.navigation_training -> {
                replaceFragment(ProfileFragment())
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_search) {
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
            //            TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.toolbar));
            //            MenuItemCompat.expandActionView(item);
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            //            fragmentTransaction.replace(R.id.home_layout, fragment);
            //            fragmentTransaction.hide(fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount() - 1));
            fragmentTransaction.add(R.id.home_container, fragment)
            fragmentTransaction.addToBackStack(null)
            //            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
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

    fun replaceFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_container, fragment)
            //            fragmentTransaction.addToBackStack(null);
            //            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            fragmentTransaction.commit()
        }
        if (fragment is MyDashboardFragment) {
            supportActionBar!!.show()
        } else {
            supportActionBar!!.hide()
        }
    }
}
