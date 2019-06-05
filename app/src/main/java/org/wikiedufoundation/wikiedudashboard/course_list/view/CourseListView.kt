package org.wikiedufoundation.wikiedudashboard.course_list.view

import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.helper.Progressive
import org.wikiedufoundation.wikiedudashboard.helper.Toaster

interface CourseListView : Progressive, Toaster {
    fun setData(data: ExploreCoursesResponse)
}
