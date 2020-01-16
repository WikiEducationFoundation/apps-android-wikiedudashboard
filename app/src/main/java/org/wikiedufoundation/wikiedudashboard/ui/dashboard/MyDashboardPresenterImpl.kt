package org.wikiedufoundation.wikiedudashboard.ui.dashboard

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.UserData
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Class to implement [requestDashboard]
 * @constructor primary constructor
 *
 * @property myDashboardView view component for course list
 * @property myDashboardProvider retrofit HTTP request call for course list
 * ***/
class MyDashboardPresenterImpl(
    private val myDashboardView: MyDashboardContract.View,
    private val myDashboardProvider: MyDashboardContract.Provider
) : MyDashboardContract.Presenter {

    override fun requestDashboard(cookies: String) {
        myDashboardView.showProgressBar(true)
        myDashboardProvider.requestCourseList(cookies, object : PresenterCallback<MyDashboardResponse> {
            override fun onSuccess(response: MyDashboardResponse) {
                myDashboardView.showProgressBar(false)
                Timber.d(response.toString())
                myDashboardView.setData(response)
            }

            override fun onFailure() {
                myDashboardView.showProgressBar(false)
                myDashboardView.showMessage("Unable to connect to server.")

                val unknownDashboardResponse =
                        MyDashboardResponse(
                                UserData(""), ArrayList()
                        )
                myDashboardView.setData(unknownDashboardResponse)
            }
        })
    }
}
