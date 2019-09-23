package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider

import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RetrofitCourseDetailProvider : CourseDetailProvider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestCourseDetail(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/course.json"
        val courseDetailResponseCall = wikiEduDashboardApi.getCourseDetail(sub_url)
        courseDetailResponseCall.enqueue(object : Callback<CourseDetailResponse> {
            override fun onResponse(call: Call<CourseDetailResponse>, response: Response<CourseDetailResponse>) {
                Timber.d(response.body().toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<CourseDetailResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d(t.message + "")
            }
        })
    }
}
