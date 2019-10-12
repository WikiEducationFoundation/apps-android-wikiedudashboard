package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter

import timber.log.Timber
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.ArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.ArticlesEditedView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Presenter to handle edited articles data and display the data in view
 * @constructor primary constructor
 * @property articlesEditedProvider edited articles api service
 * @property articlesEditedView edited article view to display data
 * ***/
class ArticlesEditedPresenterImpl(private val articlesEditedProvider: ArticlesEditedProvider, private val articlesEditedView: ArticlesEditedView) : ArticlesEditedPresenter {

    override fun requestArticlesEdited(url: String) {
        articlesEditedView.showProgressBar(true)
        articlesEditedProvider.requestArticlesEdited(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                articlesEditedView.showProgressBar(false)
                val articlesEditedResponse = o as ArticlesEdited
                Timber.d(articlesEditedResponse.toString())
                articlesEditedView.setData(articlesEditedResponse)
            }

            override fun onFailure() {
                articlesEditedView.showProgressBar(false)
                articlesEditedView.showMessage("unable to connect to server.")
            }
        })
    }
}
