package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class ArticlesEditedRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) :
    ArticlesEditedRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestArticlesEdited(url: String): List<Article> = withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi
            .getArticlesEdited(Urls.SUB_URL_COURSE_ARTICLE.format(url))
        val articleList = request.course.articles
        articleList
    }
}
