package org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter

import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.CourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

class CourseListPresenterImpl(private val courseListView: CourseListView, private val courseListProvider: CourseListProvider) : CourseListPresenter {

    override fun requestDashboard(cookies: String) {
        courseListView.showProgressBar(true)
        courseListProvider.requestCourseList(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                courseListView.showProgressBar(false)
                val exploreCoursesResponse = o as ExploreCoursesResponse
                Timber.d(exploreCoursesResponse.toString())
                courseListView.setData(exploreCoursesResponse)
            }

            override fun onFailure() {
                courseListView.showProgressBar(false)
                courseListView.showMessage("Unable to connect to server.")
            }
        })
    }
}
