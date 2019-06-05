package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view

import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.helper.Progressive
import org.wikiedufoundation.wikiedudashboard.helper.Toaster

interface CourseUploadsView : Progressive, Toaster {
    fun setData(courseUploadList: CourseUploadList)
}
