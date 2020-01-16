package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.ArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.ArticlesEditedView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Presenter to handle edited articles data and display the data in view
 * @constructor primary constructor
 * @property articlesEditedProvider edited articles api service
 * @property articlesEditedView edited article view to display data
 * ***/
class ArticlesEditedPresenterImpl(
    private val articlesEditedView: ArticlesEditedView,
    private val articlesEditedProvider: ArticlesEditedProvider
) : ArticlesEditedPresenter {

    override fun requestArticlesEdited(url: String) {
        articlesEditedView.showProgressBar(true)
        articlesEditedProvider.requestArticlesEdited(url, object : PresenterCallback<ArticlesEdited> {
            override fun onSuccess(response: ArticlesEdited) {
                articlesEditedView.showProgressBar(false)
                Timber.d(response.toString())
                articlesEditedView.setData(response)
            }

            override fun onFailure() {
                articlesEditedView.showProgressBar(false)
                articlesEditedView.showMessage("unable to connect to server.")
            }
        })
    }
}
