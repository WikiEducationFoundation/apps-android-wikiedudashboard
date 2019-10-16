package org.wikiedufoundation.wikiedudashboard.ui.mediadetail

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.view.MediaDetailFragment

/**
 * Activity for course media detail
 * ***/
class MediaDetailsActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null
    private var context: Context? = null
    private var courseUploads: CourseUploadList? = null
    private var position: Int ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        position = intent.getIntExtra("position", -1)
        courseUploads = intent.getSerializableExtra("uploads") as CourseUploadList
        context = this
        sharedPrefs = SharedPrefs(this)
        position?.let { setFragment(MediaDetailFragment.newInstance(courseUploads, it)) }
    }

    private fun setFragment(fragment: Fragment?) {
        fragment?.let {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, it)
            fragmentTransaction.commit()
        }
    }

    /**
     * To add a fragment
     *
     * @param fragment course media detail fragment
     * ***/
    fun addFragment(fragment: Fragment?) {
        fragment?.let {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.container, it)
            fragmentTransaction.commit()
        }
    }
}
