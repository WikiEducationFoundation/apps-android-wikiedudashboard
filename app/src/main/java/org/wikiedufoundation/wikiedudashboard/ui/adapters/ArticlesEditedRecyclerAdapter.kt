package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_articles_edited.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article
import java.util.*

/**
 * A RecyclerView adapter for edited articles
 * @property context Context
***/
class ArticlesEditedRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var edited: List<Article> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(context).inflate(R.layout.item_rv_articles_edited, viewGroup, false)
        return ArticlesEditedViewHolder(view1)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val articlesEditedViewHolder = viewHolder as ArticlesEditedViewHolder
        articlesEditedViewHolder.tvCountArticlesEditedTitle.text = edited[i].title
    }

    /**
     * Use [setData] to set a list of edited articles
     * @param edited A list of edited articles
     ***/
    fun setData(edited: List<Article>) {
        this.edited = edited
    }

    override fun getItemCount(): Int {
        return edited.size
    }

    /**
     * To initialize [tvCountArticlesEditedTitle] TextView
     * @property itemView used to call textView
     ***/
    inner class ArticlesEditedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountArticlesEditedTitle: TextView = itemView.tv_count_articles_edited_title
    }
}
