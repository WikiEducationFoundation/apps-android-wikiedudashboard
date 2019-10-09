package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData

/**
 * RecyclerView adapter for courses list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class ProfileCourseListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter<CourseData>(layoutId) {

    fun onCourseClicked(course: CourseData) {
        onClickListener(course.courseSlug)
    }

}