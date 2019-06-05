package org.wikiedufoundation.wikiedudashboard.course_list.presenter

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.course_list.provider.CourseListProvider
import org.wikiedufoundation.wikiedudashboard.course_list.view.CourseListView
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

class CourseListPresenterImpl(private val courseListView: CourseListView, private val courseListProvider: CourseListProvider) : CourseListPresenter {

    override fun requestDashboard(cookies: String) {
        courseListView.showProgressBar(true)
        courseListProvider.requestCourseList(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                courseListView.showProgressBar(false)
                val exploreCoursesResponse = o as ExploreCoursesResponse
                Log.d("Presenter: ", exploreCoursesResponse.toString())
                courseListView.setData(exploreCoursesResponse)
            }

            override fun onFailure() {
                courseListView.showProgressBar(false)
                courseListView.showMessage("Unable to connect to server.")

            }
        })
    }
}
