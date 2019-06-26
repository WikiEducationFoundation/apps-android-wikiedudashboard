package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_rv_campaign_list.view.tv_campaign_title

import org.wikiedufoundation.wikiedudashboard.R

import java.util.ArrayList

import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.view.CampaignListFragment

class CampaignListRecyclerAdapter internal constructor(
    private val context: Context,
    private var campaignListFragment: CampaignListFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var campaigns: List<CampaignListData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyDashboardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_campaign_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val campaign: CampaignListData? = campaigns[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvCampaignTitle.text = campaign!!.title
//        holder.itemView.setOnClickListener { campaignListFragment.openCourseDetail(campaigns[position].slug) }
    }

    fun setData(campaigns: List<CampaignListData>) {
        this.campaigns = campaigns
    }

    override fun getItemCount(): Int {
        return campaigns.size
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCampaignTitle: TextView = itemView.tv_campaign_title
    }
}