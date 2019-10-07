package org.wikiedufoundation.wikiedudashboard.ui.campaign.data


/**
 * [CampaignListData] model class
 * @constructor primary constructor
 *
 * @property id Integer, campaign id
 * @property title String, campaign title
 * @property slug String, campaign slug
 * @property description String, campaign description
 * ***/
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
