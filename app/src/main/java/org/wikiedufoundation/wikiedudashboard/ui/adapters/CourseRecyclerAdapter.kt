package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course

/**
 * RecyclerView adapter for recent activity list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class CourseRecyclerAdapter(
    layoutId: Int,
    private val onClickListener: (Course) -> Unit
) : SingleLayoutAdapter<Course>(layoutId) {

    /**
     * Use [onStudentClicked] to handle the item's click
     * @param user item's object
     ***/
    fun onCourseClicked(course: Course) {
        onClickListener(course)
    }
}
