package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider

import android.util.Log
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitStudentListProvider : StudentListProvider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestStudentList(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/users.json"
        val studentListCall = wikiEduDashboardApi.getStudentList(sub_url)
        studentListCall.enqueue(object : Callback<StudentListResponse> {
            override fun onResponse(call: Call<StudentListResponse>, response: Response<StudentListResponse>) {
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<StudentListResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Log.d("Failure: ", t.message + "")
            }
        })
    }
}
