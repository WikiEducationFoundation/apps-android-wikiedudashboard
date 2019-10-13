package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        private var profileCourseListClickListener: ProfileCourseListClickListener ? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var courses: List<CourseData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_explore_courses, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val course = courses[position]
        (holder as? MyDashboardViewHolder)?.setViewHolder(course)
        holder.itemView.setOnClickListener { profileCourseListClickListener?.onCourseClicked(courses[position].courseSlug) }

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
     * Listener for events related with the list interactions
     */
    interface ProfileCourseListClickListener {
        /**
         * This method is called when a course from the list have been clicked
         * @param courseSlug Slug of selected course
         * ***/
        fun onCourseClicked(courseSlug: String)
    }

    /**
     * Use [MyDashboardViewHolder] inner class to declare Views component
     * @property itemView primary constructor property to call [tv_course_title]
     * ***/

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Sets the ViewHolder with the data of the [CourseData] provided
         * @param course [CourseData] that is going to be shown in the [ViewHolder]
         * ***/
        fun setViewHolder(course: CourseData) {
            itemView.tvCourseTitle.text = course?.courseTitle
        }
    }
}