package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_recent_activity.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity
import java.text.SimpleDateFormat
import java.util.*


class RecentActivityRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var ctx: Context
    private var activities: List<RecentActivity> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        ctx = viewGroup.context
        val view1 = LayoutInflater.from(ctx).inflate(R.layout.item_rv_recent_activity, viewGroup, false)
        return ArticlesEditedViewHolder(view1)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val articlesEditedViewHolder = viewHolder as ArticlesEditedViewHolder
        articlesEditedViewHolder.tvCountArticlesEditedTitle.text = activities[i].title
        articlesEditedViewHolder.tvCountCharactersAdded.text = activities[i].characters.toString()
        articlesEditedViewHolder.tvRevisor.text = activities[i].revisor

        val dateFormat = SimpleDateFormat(ctx.getString(R.string.time_format_12_hours))
        val formattedDate = dateFormat.format(activities[i].date).toString()

        articlesEditedViewHolder.tvDate.text = formattedDate
    }

    fun setData(edited: List<RecentActivity>) {
        this.activities = edited
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    inner class ArticlesEditedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountArticlesEditedTitle: TextView = itemView.tv_count_articles_edited_title
        val tvCountCharactersAdded: TextView = itemView.tv_count_characters_added
        val tvRevisor: TextView = itemView.tv_revisor
        val tvDate: TextView = itemView.tv_date
    }
}
