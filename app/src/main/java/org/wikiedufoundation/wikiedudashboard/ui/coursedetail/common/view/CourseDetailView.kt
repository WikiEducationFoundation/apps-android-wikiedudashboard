package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CourseDetailView : Progressive, Toaster {
    fun setData(data: CourseDetail)
}
