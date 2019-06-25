package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface ArticlesEditedProvider {
    fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>)
}
