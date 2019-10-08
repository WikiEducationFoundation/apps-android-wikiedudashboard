package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider

import timber.log.Timber
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit http request for edited articles
 * ***/
class RetrofitArticlesEditedProvider : ArticlesEditedProvider {
    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/articles.json"
        val articlesEditedResponseCall = wikiEduDashboardApi.getArticlesEdited(sub_url)
        articlesEditedResponseCall.enqueue(object : Callback<ArticlesEdited> {
            override fun onResponse(call: Call<ArticlesEdited>, response: Response<ArticlesEdited>) {
                Timber.d(response.body()!!.course.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ArticlesEdited>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d(t.message + "")
            }
        })
    }
}
