package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**
 * RecyclerView adapter for campaign list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class CampaignListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter<CampaignListData>(layoutId) {

    fun onCampaignClicked(campaign: CampaignListData) {
        onClickListener(campaign.slug)
    }

}