package org.wikiedufoundation.wikiedudashboard.ui.dashboard.data

/**
 * [MyDashboardResponse] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class MyDashboardResponse(
        val user: UserData,
        val currentCourses: List<CourseListData>
)
