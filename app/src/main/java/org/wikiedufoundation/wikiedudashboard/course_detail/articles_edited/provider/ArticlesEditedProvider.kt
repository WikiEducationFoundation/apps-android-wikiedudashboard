package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.provider

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

interface ArticlesEditedProvider {
    fun requestArticlesEdited(url: String, presenterCallback: PresenterCallback<*>)
}
