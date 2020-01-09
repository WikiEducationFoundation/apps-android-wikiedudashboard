package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * [RetrofitStudentListProvider] to make retrofit http request for getting list of student data
 * ***/
class RetrofitStudentListProvider(
        private val wikiEduDashboardApi: WikiEduDashboardApi
) : StudentListProvider {
//     = ProviderUtils.apiObject

    override fun requestStudentList(url: String, presenterCallback: PresenterCallback<StudentListResponse>) {
        val subUrl = "courses/$url/users.json"
        val studentListCall = wikiEduDashboardApi.getStudentList(subUrl)
        studentListCall.enqueue(object : Callback<StudentListResponse> {
            override fun onResponse(call: Call<StudentListResponse>, response: Response<StudentListResponse>) {
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<StudentListResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d("${t.message} ")
            }
        })
    }
}
