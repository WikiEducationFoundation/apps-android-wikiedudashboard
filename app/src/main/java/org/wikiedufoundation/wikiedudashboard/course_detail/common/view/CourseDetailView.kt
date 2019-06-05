package org.wikiedufoundation.wikiedudashboard.course_detail.common.view

import org.wikiedufoundation.wikiedudashboard.course_detail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.helper.Progressive
import org.wikiedufoundation.wikiedudashboard.helper.Toaster

interface CourseDetailView : Progressive, Toaster {
    fun setData(data: CourseDetail)
}
