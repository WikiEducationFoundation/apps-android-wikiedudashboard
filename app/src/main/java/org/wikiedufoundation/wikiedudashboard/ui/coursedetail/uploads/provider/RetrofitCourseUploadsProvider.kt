package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Class that implementing [requestCourseUploads] method to get course uploads data
 * ***/
class RetrofitCourseUploadsProvider(
    private val wikiEduDashboardApi: WikiEduDashboardApi
) : CourseUploadsProvider {
//     = ProviderUtils.apiObject

    override fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<CourseUploadResponse>) {
        val subUrl = "courses/$url/uploads.json"
        val courseDetailResponseCall = wikiEduDashboardApi.getCourseUploads(subUrl)
        courseDetailResponseCall.enqueue(object : Callback<CourseUploadResponse> {
            override fun onResponse(call: Call<CourseUploadResponse>, response: Response<CourseUploadResponse>) {

                Timber.d("${response.body()?.course.toString()} ")
                response.body()?.let { presenterCallback.onSuccess(it) }

            }

            override fun onFailure(call: Call<CourseUploadResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d("$t.message ")
            }
        })
    }
}
