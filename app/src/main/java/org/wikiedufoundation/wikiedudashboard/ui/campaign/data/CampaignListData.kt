package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


/**
 * [CampaignListData] model class
 * @constructor primary constructor
 *
 * @property id Integer, campaign id
 * @property title String, campaign title
 * @property slug String, campaign slug
 * @property description String, campaign description
 * ***/

@Entity(tableName = "campaign_list_table")
class CampaignListData(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("slug")
        val slug: String,
        @SerializedName("description")
        val description: String
) {
    override fun toString(): String {
        return "CampaignListData(" +
                "id=$id, " +
                "title='$title', " +
                "slug='$slug', " +
                "description='$description')"
    }
}
