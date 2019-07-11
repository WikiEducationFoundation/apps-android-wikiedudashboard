package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider

import android.util.Log
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitArticlesEditedProvider : ArticlesEditedProvider {
    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/articles.json"
        val articlesEditedResponseCall = wikiEduDashboardApi.getArticlesEdited(sub_url)
        articlesEditedResponseCall.enqueue(object : Callback<ArticlesEdited> {
            override fun onResponse(call: Call<ArticlesEdited>, response: Response<ArticlesEdited>) {
                Log.d("Success: ", response.body()!!.course.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ArticlesEdited>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Log.d("Failure: ", t.message + "")
            }
        })
    }
}
