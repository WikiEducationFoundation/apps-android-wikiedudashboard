package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article

/**
 *Declares ArticlesEditedRepository interface
 * */
interface ArticlesEditedRepository {

    /**
     *Creates a suspend function [requestArticlesEdited]
     * */
    suspend fun requestArticlesEdited(url: String): List<Article>
}