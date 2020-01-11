package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article

/**
 * A RecyclerView adapter for edited articles
 * @property layoutId id
 ***/
class ArticlesEditedRecyclerAdapter(
    layoutId: Int
) : SingleLayoutAdapter<Article>(layoutId)
