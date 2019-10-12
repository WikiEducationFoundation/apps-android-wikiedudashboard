package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider

import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RetrofitCourseUploadsProvider : CourseUploadsProvider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/uploads.json"
        val courseDetailResponseCall = wikiEduDashboardApi.getCourseUploads(sub_url)
        courseDetailResponseCall.enqueue(object : Callback<CourseUploadResponse> {
            override fun onResponse(call: Call<CourseUploadResponse>, response: Response<CourseUploadResponse>) {
                Timber.d("${response.body()?.course.toString()} ")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<CourseUploadResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d("$t.message ")
            }
        })
    }
}
