package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_my_dashboard.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.FileUsage
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.view.MediaDetailFragment
import java.util.*

class FileUsesRecyclerAdapter internal constructor(private val context: Context, private var mediaDetailFragment: MediaDetailFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var courses: List<FileUsage> = ArrayList()
    private var course: FileUsage? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(context).inflate(R.layout.item_rv_media_category, parent, false)
        return CategoryViewHolder(view1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        course = courses[position]
        val myDashboardViewHolder = holder as CategoryViewHolder
        myDashboardViewHolder.tvCourseTitle.text = course!!.title
    }

    fun setData(courses: List<FileUsage>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCourseTitle: TextView = itemView.tv_course_title
    }
}