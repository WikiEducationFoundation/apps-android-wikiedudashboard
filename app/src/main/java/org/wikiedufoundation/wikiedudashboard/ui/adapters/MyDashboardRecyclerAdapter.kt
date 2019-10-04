package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_my_dashboard.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment
import java.util.*

class MyDashboardRecyclerAdapter internal constructor(
        private var myDashboardFragment: MyDashboardFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var courses: List<CourseListData> = ArrayList()
    private var course: CourseListData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_my_dashboard, parent, false)
        return MyDashboardViewHolder(view1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        course = courses[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvCourseTitle.text = course?.title
        holder.itemView.setOnClickListener { myDashboardFragment.openCourseDetail(courses[position].slug) }
    }

    fun setData(courses: List<CourseListData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCourseTitle: TextView = itemView.tv_course_title
    }
}