package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article
import java.util.*

/**
 * A RecyclerView adapter for edited articles
 * @property context Context
 ***/
class ArticlesEditedRecyclerAdapter(
        layoutId: Int
) : SingleLayoutAdapter(layoutId) {
    private var edited: List<Article> = ArrayList()

    override fun getObjForPosition(position: Int): Any = edited[position]

    fun setData(edited: List<Article>) {
        this.edited = edited
    }

    override fun getItemCount(): Int = edited.size

}
