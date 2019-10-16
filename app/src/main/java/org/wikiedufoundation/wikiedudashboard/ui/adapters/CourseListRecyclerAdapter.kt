package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData

/**
 * RecyclerView adapter for courses list data
 * @constructor secondary constructor to initialize [CourseListFragment] variable
 * ***/
class CourseListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onItemClickListener: (String) -> Unit
) : SingleLayoutAdapter<CourseListData>(layoutId) {

    /**
     * Use [onCourseClicked] to handle the item's click
     * @param course item's object
     ***/
    fun onCourseClicked(course: CourseListData) {
        onItemClickListener(course.slug)
    }

}