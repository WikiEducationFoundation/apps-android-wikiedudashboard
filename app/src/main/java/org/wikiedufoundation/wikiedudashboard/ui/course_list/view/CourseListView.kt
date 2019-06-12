package org.wikiedufoundation.wikiedudashboard.ui.course_list.view

import org.wikiedufoundation.wikiedudashboard.ui.course_list.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CourseListView : Progressive, Toaster {
    fun setData(data: ExploreCoursesResponse)
}
