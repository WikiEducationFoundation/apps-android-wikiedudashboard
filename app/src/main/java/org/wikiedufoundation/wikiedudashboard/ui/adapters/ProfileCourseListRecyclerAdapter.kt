package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData

class ProfileCourseListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter(layoutId) {
    private var courses: List<CourseData> = ArrayList()

    override fun getObjForPosition(position: Int): Any = courses[position]

    override fun getItemCount(): Int = courses.size

    /**
     * Set [CourseData] type list of courses
     * @param courses list of courses
     * ***/
    fun setData(courses: List<CourseData>) {
        this.courses = courses
    }

    fun onCourseClicked(course: CourseData) {
        onClickListener(course.courseSlug)
    }

}