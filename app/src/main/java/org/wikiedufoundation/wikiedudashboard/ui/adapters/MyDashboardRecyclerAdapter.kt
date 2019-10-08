package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.view.MyDashboardFragment
import java.util.*

/**
 * RecyclerView adapter for my dashboard
 * @constructor secondary constructor declaring [MyDashboardFragment] variable
 * ***/
class MyDashboardRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter(layoutId) {
    private var courses: List<CourseListData> = ArrayList()

    override fun getObjForPosition(position: Int): Any = courses[position]

    /**
     * Set [CourseListData] type list of courses
     * @param courses list of courses
     * ***/
    fun setData(courses: List<CourseListData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int = courses.size

    fun onItemClicked(course: CourseListData) {
        onClickListener(course.slug)
    }

}