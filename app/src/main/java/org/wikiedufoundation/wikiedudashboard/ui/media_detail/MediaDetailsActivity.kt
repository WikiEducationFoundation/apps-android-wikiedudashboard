package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs

import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadList

class MediaDetailsActivity : AppCompatActivity(){

    private var sharedPrefs: SharedPrefs? = null
    private var context: Context? = null
    private var courseUploads: CourseUploadList? =null
    private var position: Int ?=null
    private var toolbar: Toolbar ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)
        toolbar = findViewById(R.id.toolbar)
        position = intent.getIntExtra("position", -1)
        courseUploads = intent.getSerializableExtra("uploads") as CourseUploadList
        context = this
        sharedPrefs = SharedPrefs(this)
        toolbar!!.setNavigationOnClickListener { onBackPressed() }
        addFragment(MediaDetailFragment.newInstance(courseUploads, position!!))
    }

    private fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }

}
