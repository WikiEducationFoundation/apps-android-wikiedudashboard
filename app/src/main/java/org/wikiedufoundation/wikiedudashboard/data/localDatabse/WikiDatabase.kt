package org.wikiedufoundation.wikiedudashboard.data.localDatabse

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
* This setup the Room Database to enable
 * offline storage of data
**/
@Database(entities = [CampaignListData::class, CourseListData::class], version = 1, exportSchema = false)
abstract class WikiDatabase : RoomDatabase() {
    abstract val activeCampaignDao: ActiveCampaignDao
    abstract val courseListDao: CourseListDao
}