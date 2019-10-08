package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import java.util.*

/**
 * RecyclerView adapter for courses list data
 * @property courseUploadsFragment primary constructor property
 * ***/
class CourseUploadsRecyclerAdapter(
        layoutId: Int,
        private val onClickListener: (CourseUploadList, Int) -> Unit
) : SingleLayoutAdapter(layoutId) {
    private lateinit var courseUploadList: CourseUploadList
    private var courseUploads: List<CourseUpload> = ArrayList()

    override fun getObjForPosition(position: Int): Any = courseUploads[position]

    /**
     * Set [courseUploads] list of data
     * @param courseUploads list of courses
     * ***/
    fun setData(courseUploads: CourseUploadList) {
        this.courseUploads = courseUploads.uploads
        this.courseUploadList = courseUploads
    }

    override fun getItemCount(): Int = courseUploads.size

    fun onCourseUpdatedClicked(course: CourseUpload) {
        val position = courseUploads.indexOf(course)
        onClickListener(courseUploadList, position)
    }
}