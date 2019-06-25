package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCourseUploadsProvider : CourseUploadsProvider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/uploads.json"
        val courseDetailResponseCall = wikiEduDashboardApi.getCourseUploads(sub_url)
        courseDetailResponseCall.enqueue(object : Callback<CourseUploadResponse> {
            override fun onResponse(call: Call<CourseUploadResponse>, response: Response<CourseUploadResponse>) {
                Log.d("Success: ", response.body()!!.course.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<CourseUploadResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Log.d("Failure: ", t.message + "")
            }
        })
    }
}
