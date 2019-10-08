package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_campaign_list.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.view.CampaignListFragment
import java.util.*

/**
 * RecyclerView adapter for campaign list data
 * ***/
class CampaignListRecyclerAdapter internal constructor(
    private var campaignListFragment: CampaignListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var campaigns: List<CampaignListData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_campaign_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val campaign: CampaignListData? = campaigns[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvCampaignTitle.text = campaign!!.title
//        holder.itemView.setOnClickListener { campaignListFragment.openCourseDetail(campaigns[position].slug) }
    }

    /**
     * Use [setData] to set a list of campaign data
     * @param campaigns A list of campaign data
     ***/
    fun setData(campaigns: List<CampaignListData>) {
        this.campaigns = campaigns
    }

    override fun getItemCount(): Int {
        return campaigns.size
    }

    /**
     * Nested class that initializes [tvCampaignTitle]
     *
     * @property itemView to call [tv_campaign_title]
     * ***/
    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCampaignTitle: TextView = itemView.tv_campaign_title
    }
}