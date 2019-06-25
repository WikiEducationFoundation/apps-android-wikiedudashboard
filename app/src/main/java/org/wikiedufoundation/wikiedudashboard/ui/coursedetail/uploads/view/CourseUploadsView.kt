package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CourseUploadsView : Progressive, Toaster {
    fun setData(courseUploadList: CourseUploadList)
}
