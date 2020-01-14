package org.wikiedufoundation.wikiedudashboard.ui.dashboard.data

import com.google.gson.annotations.SerializedName
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
 * [MyDashboardResponse] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class MyDashboardResponse(
    @SerializedName("user")
    val user: UserData,
    @SerializedName("current_courses")
    val currentCourses: List<CourseListData>
)
