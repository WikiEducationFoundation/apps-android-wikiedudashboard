package org.wikiedufoundation.wikiedudashboard.ui.course_detail.articles_edited.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface ArticlesEditedProvider {
    fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>)
}
