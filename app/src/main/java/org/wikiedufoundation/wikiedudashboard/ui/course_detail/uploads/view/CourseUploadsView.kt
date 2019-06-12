package org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.view

import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CourseUploadsView : Progressive, Toaster {
    fun setData(courseUploadList: CourseUploadList)
}
