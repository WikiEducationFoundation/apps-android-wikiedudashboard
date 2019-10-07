package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_explore_courses.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import java.util.*

/**
 * RecyclerView adapter for courses list data
 * @constructor secondary constructor to initialize [CourseListFragment] variable
 * ***/
class CourseListRecyclerAdapter internal constructor(
    private var courseListFragment: CourseListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var courses: List<CourseListData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_explore_courses, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val course: CourseListData? = courses[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvCourseTitle.text = course!!.title
        holder.itemView.setOnClickListener { courseListFragment.openCourseDetail(courses[position].slug) }
    }

    /**
     * Set [CourseListData] type [courses] list
     * @param courses list of courses
     * ***/
    fun setData(courses: List<CourseListData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    /**
     * Use [MyDashboardViewHolder] inner class to declare [tvCourseTitle] TextView
     * @property itemView primary constructor property to call [tv_course_title]
     * ***/
    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCourseTitle: TextView = itemView.tv_course_title
    }
}