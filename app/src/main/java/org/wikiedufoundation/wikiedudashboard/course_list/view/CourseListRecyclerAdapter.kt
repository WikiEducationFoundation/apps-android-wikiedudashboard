package org.wikiedufoundation.wikiedudashboard.course_list.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.dashboard.data.CourseListData

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

class CourseListRecyclerAdapter internal constructor(private val context: Context, internal var courseListFragment: CourseListFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var courses: List<CourseListData> = ArrayList()
    private var course: CourseListData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = layoutInflater.inflate(R.layout.item_rv_explore_courses, parent, false)
        return MyDashboardViewHolder(view1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

//        course = courses[position]
//        val myDashboardViewHolder = holder as MyDashboardViewHolder
//        myDashboardViewHolder.tv_course_title!!.text = course!!.title
//        holder.itemView.setOnClickListener { view -> courseListFragment.openCourseDetail(courses[position].slug) }
    }


    fun setData(courses: List<CourseListData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_course_title)
        internal var tv_course_title: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}