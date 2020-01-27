package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * [RecentActivity] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
@Entity(tableName = "recent_activity")
data class RecentActivity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("revisor")
    val revisor: String?,
    @SerializedName("characters")
    val characters: Int?,
    @Embedded
    @SerializedName("date")
    val date: Date?
)
