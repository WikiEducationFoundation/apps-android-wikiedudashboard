package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

class CampaignListData(
        val id: Int,
        val title: String,
        val slug: String,
        val description: String
) {
    override fun toString(): String {
        return "CampaignListData(id=$id, title='$title', slug='$slug', description='$description')"
    }
}
