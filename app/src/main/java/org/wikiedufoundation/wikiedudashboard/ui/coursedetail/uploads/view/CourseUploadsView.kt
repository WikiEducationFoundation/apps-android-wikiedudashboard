package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * [CourseUploadsView] interface that defines [setData]
 * ***/
interface CourseUploadsView : Progressive, Toaster {
    /**
     * Use [setData] to set a list of course upload data
     *
     * @param courseUploadList list of course upload
     * ***/
    fun setData(courseUploadList: CourseUploadList)
}
