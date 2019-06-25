package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface ArticlesEditedProvider {
    fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>)
}
