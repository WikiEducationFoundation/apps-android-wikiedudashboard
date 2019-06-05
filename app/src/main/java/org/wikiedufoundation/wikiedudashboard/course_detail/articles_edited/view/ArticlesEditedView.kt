package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.view

import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data.ArticlesEdited

import org.wikiedufoundation.wikiedudashboard.helper.Progressive
import org.wikiedufoundation.wikiedudashboard.helper.Toaster

interface ArticlesEditedView : Progressive, Toaster {
    fun setData(data: ArticlesEdited)
}
