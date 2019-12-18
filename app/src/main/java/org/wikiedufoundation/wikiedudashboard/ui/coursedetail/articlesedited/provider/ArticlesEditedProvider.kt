package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Interface defining http request function for edited articles
 * ***/
interface ArticlesEditedProvider {
    /**
     * Http request function for edited articles
     *
     * @param url Request api url
     * @param presenterCallback
     * ***/
    fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<ArticlesEdited>)
}
