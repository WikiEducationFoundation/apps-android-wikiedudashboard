package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Retrofit http request for edited articles
 * ***/
class RetrofitArticlesEditedProvider(
    private val wikiEduDashboardApi: WikiEduDashboardApi
) : ArticlesEditedProvider {
//     = ProviderUtils.apiObject

    override fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<ArticlesEdited>) {
        val subUrl = "courses/$url/articles.json"
        val articlesEditedResponseCall = wikiEduDashboardApi.getArticlesEdited(subUrl)
        articlesEditedResponseCall.enqueue(object : Callback<ArticlesEdited> {
            override fun onResponse(call: Call<ArticlesEdited>, response: Response<ArticlesEdited>) {
                Timber.d(response.body()?.course.toString() + "")
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
