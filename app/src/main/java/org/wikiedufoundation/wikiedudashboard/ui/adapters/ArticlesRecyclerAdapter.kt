package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles

/**
 * A RecyclerView adapter for edited articles
 * @property layoutId id
 ***/
class ArticlesRecyclerAdapter(
    layoutId: Int,
    private val onClickListener: (Articles) -> Unit
) : SingleLayoutAdapter<Articles>(layoutId) {

    /**
     * Use [onArticleClicked] to handle the item's click
     * @param user item's object
     ***/
    fun onArticleClicked(articles: Articles) {
        onClickListener(articles)
    }
}
