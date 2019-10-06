package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_explore_courses.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData
import org.wikiedufoundation.wikiedudashboard.ui.profile.view.ProfileCourseListFragment
import java.util.*

class ProfileCourseListRecyclerAdapter internal constructor(
    private val context: Context,
    private var profileCourseListClickListener: ProfileCourseListClickListener ? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var courses: List<CourseData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_explore_courses, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val course = courses[position]
        (holder as? MyDashboardViewHolder)?.setViewHolder(course)
        holder.itemView.setOnClickListener { profileCourseListClickListener?.onCourseClicked(courses[position].course_slug) }
    }

    fun setData(courses: List<CourseData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    interface ProfileCourseListClickListener {
        fun onCourseClicked(courseSlug: String)
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setViewHolder(course: CourseData) {
            itemView.tvCourseTitle.text = course?.course_title
        }
    }
}