package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_explore_courses.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData
import org.wikiedufoundation.wikiedudashboard.ui.profile.view.ProfileCourseListFragment
import java.util.*

/**
 * RecyclerView adapter for profile course
 * @constructor secondary constructor declaring [ProfileCourseListFragment] variable
 * ***/
class ProfileCourseListRecyclerAdapter internal constructor(
        private var profileCourseListFragment: ProfileCourseListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var courses: List<CourseData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_explore_courses, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val course: CourseData? = courses[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvCourseTitle.text = course?.courseTitle
        holder.itemView.setOnClickListener { profileCourseListFragment.openCourseDetail(courses[position].courseTitle) }
    }

    /**
     * Set [CourseData] type list of courses
     * @param courses list of courses
     * ***/
    fun setData(courses: List<CourseData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    /**
     * Use [MyDashboardViewHolder] inner class to declare Views component
     * @property itemView primary constructor property to call [tv_course_title]
     * ***/
    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCourseTitle: TextView = itemView.tv_course_title
    }
}