package org.wikiedufoundation.wikiedudashboard.ui.dashboard

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class MyDashboardPresenterImpl(private val myDashboardView: MyDashboardContract.View, private val myDashboardProvider: MyDashboardContract.Provider) : MyDashboardContract.Presenter {

    override fun requestDashboard(cookies: String) {
        myDashboardView.showProgressBar(true)
        myDashboardProvider.requestCourseList(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                myDashboardView.showProgressBar(false)
                val myDashboardResponse = o as MyDashboardResponse
                Log.d("Presenter: ", myDashboardResponse.toString())
                myDashboardView.setData(myDashboardResponse)
            }

            override fun onFailure() {
                myDashboardView.showProgressBar(false)
                myDashboardView.showMessage("Unable to connect to server.")
            }
        })
    }
}
