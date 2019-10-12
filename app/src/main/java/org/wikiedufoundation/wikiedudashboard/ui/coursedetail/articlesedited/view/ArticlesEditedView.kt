package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * Edited articles view interface
 * ***/
interface ArticlesEditedView : Progressive, Toaster {
    /**
     * Set edited articles data
     * @param data edited articles data
     * ***/
    fun setData(data: ArticlesEdited)
}
