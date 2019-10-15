package org.wikiedufoundation.wikiedudashboard.ui.profile

import android.content.Context
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
    private var context: Context? = null
    private var username: String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        username = intent.getStringExtra("username")
        context = this
        sharedPrefs = SharedPrefs(this)
        username?.let { setFragment(ProfileFragment.newInstance(it, true)) }
    }

    /***
     * Put [fragment] variables into param to initialize fragment
     * @param fragment profile fragments that needs to be set at first place
     * ***/
    private fun setFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }

    /**
     * Use [addFragment] to add new fragments to [ProfileActivity]
     * @param fragment any fragment need to be add
     * ***/
    fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }
}
