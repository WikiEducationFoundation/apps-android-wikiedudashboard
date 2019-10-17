package org.wikiedufoundation.wikiedudashboard.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.profile.view.ProfileFragment

/**
 * An activity for user profile
 * ***/
class ProfileActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null

    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        userName = intent.getStringExtra("username")

        sharedPrefs = SharedPrefs(this)
        setFragment(ProfileFragment.newInstance(userName, true))
    }

    private fun setFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container, fragment)
                commit()
            }
        }
    }

    /**
     * Use [addFragment] to add new fragments to [ProfileActivity]
     * @param fragment any fragment need to be add
     * ***/
    fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().apply {
                addToBackStack(null)
                replace(R.id.container, fragment)
                commit()
            }
        }
    }
}
