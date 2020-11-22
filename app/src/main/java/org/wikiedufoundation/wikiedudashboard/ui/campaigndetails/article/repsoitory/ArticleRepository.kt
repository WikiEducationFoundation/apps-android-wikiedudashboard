package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.repsoitory

import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles

/**
 *Declares ArticleRepository interface
 * */
interface ArticleRepository {

    /**
     *Creates a suspend function [requestArticles]
     * */
    suspend fun requestArticles(url: String): List<Articles>
}
