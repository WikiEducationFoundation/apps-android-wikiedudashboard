package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import java.util.*

/**
 * RecyclerView adapter for courses list data
 * @constructor secondary constructor to initialize [CourseListFragment] variable
 * ***/
class CourseListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onItemClickListener: (String) -> Unit
) : SingleLayoutAdapter(layoutId) {

    private var courses: List<CourseListData> = ArrayList()

    override fun getObjForPosition(position: Int): Any = courses[position]

    /**
     * Set [CourseListData] type [courses] list
     * @param courses list of courses
     * ***/
    fun setData(courses: List<CourseListData>) {
        this.courses = courses
    }

    override fun getItemCount(): Int = courses.size

    fun onCourseClicked(course: CourseListData) {
        onItemClickListener(course.slug)
    }

}