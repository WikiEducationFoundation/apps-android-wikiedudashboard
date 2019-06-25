package org.wikiedufoundation.wikiedudashboard.ui.courselist.view

import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CourseListView : Progressive, Toaster {
    fun setData(data: ExploreCoursesResponse)
}
