package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.presenter

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.provider.ArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.view.ArticlesEditedView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class ArticlesEditedPresenterImpl(private val articlesEditedProvider: ArticlesEditedProvider, private val articlesEditedView: ArticlesEditedView) : ArticlesEditedPresenter {

    override fun requestArticlesEdited(url: String) {
        articlesEditedView.showProgressBar(true)
        articlesEditedProvider.requestArticlesEdited(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                articlesEditedView.showProgressBar(false)
                val articlesEditedResponse = o as ArticlesEdited
                Log.d("Presenter: ", articlesEditedResponse.toString())
                articlesEditedView.setData(articlesEditedResponse)
            }

            override fun onFailure() {
                articlesEditedView.showProgressBar(false)
                articlesEditedView.showMessage("unable to connect to server.")
            }
        })
    }
}
