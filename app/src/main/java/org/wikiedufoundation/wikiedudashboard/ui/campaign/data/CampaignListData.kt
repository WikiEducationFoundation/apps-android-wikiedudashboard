package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity(tableName = "campaign_list")
data class CampaignListData(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("description")
    val description: String?
)
