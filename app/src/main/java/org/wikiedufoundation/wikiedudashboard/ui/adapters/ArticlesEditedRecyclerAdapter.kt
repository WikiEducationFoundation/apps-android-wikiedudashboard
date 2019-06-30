package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import org.wikiedufoundation.wikiedudashboard.R

import java.util.ArrayList

import kotlinx.android.synthetic.main.item_rv_articles_edited.view.*
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article

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

    fun setData(edited: List<Article>) {
        this.edited = edited
    }

    override fun getItemCount(): Int {
        return edited.size
    }

    inner class ArticlesEditedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountArticlesEditedTitle: TextView = itemView.tv_count_articles_edited_title
    }
}
