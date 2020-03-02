package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.repsoitory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class ArticleRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) : ArticleRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestArticles(url: String): List<Articles> =
            withContext(Dispatchers.IO) {
                val request = wikiEduDashboardApi
                        .getArticles(Urls.SUB_URL_CAMPAIGN_ARTICLE.format(url))
                val articleList = request.article
                articleList
    }
}