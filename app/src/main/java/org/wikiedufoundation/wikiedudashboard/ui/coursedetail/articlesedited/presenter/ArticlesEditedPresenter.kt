package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter

/**
 * Interface defining http request function for edited articles
 * ***/
interface ArticlesEditedPresenter {
    /**
     * Http request function for edited articles
     * @param url edited articles API url
     * ***/
    fun requestArticlesEdited(url: String)
}
