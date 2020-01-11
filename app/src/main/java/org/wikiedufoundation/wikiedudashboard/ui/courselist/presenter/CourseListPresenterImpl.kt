package org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter

import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.CourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Class to implement [requestDashboard]
 * @constructor primary constructor
 *
 * @property courseListView view component for course list
 * @property courseListProvider retrofit HTTP request call for course list
 * ***/
class CourseListPresenterImpl(
    private val courseListView: CourseListView,
    private val courseListProvider: CourseListProvider
) : CourseListPresenter {

    override fun requestDashboard(cookies: String) {
        courseListView.showProgressBar(true)
        courseListProvider.requestCourseList(cookies, object : PresenterCallback<ExploreCoursesResponse> {
            override fun onSuccess(exploreCoursesResponse: ExploreCoursesResponse) {
                courseListView.showProgressBar(false)
                Timber.d(exploreCoursesResponse.toString())
                courseListView.setData(exploreCoursesResponse)
            }

            override fun onFailure() {
                courseListView.showProgressBar(false)
                courseListView.showMessage("Unable to connect to server.")

                val unknownExploreCoursesResponse = ExploreCoursesResponse(ArrayList())
                courseListView.setData(unknownExploreCoursesResponse)
            }
        })
    }
}
