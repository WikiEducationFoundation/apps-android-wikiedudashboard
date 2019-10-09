package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList

/**
 * RecyclerView adapter for courses list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class CourseUploadsRecyclerAdapter(
        layoutId: Int,
        private val onClickListener: (CourseUploadList, Int) -> Unit
) : SingleLayoutAdapter<CourseUpload>(layoutId) {

    fun onCourseUpdatedClicked(course: CourseUpload) {
        val position = data.indexOf(course)
        onClickListener(CourseUploadList(data), position)
    }
}