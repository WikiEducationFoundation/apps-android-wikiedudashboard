package org.wikiedufoundation.wikiedudashboard.data.localDatabse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**
* This setup the Room Database to enable
 * offline storage of data
**/
@Database(entities = [CampaignListData::class], version = 1, exportSchema = false)
abstract class WikiDatabase : RoomDatabase() {
    abstract val activeCampaignDao : ActiveCampaignDao

}

private lateinit var INSTANCE : WikiDatabase

/**
 * This creates a single database instance
 *
 **/
fun database(context: Context) : WikiDatabase{
    synchronized(WikiDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WikiDatabase::class.java, "WikiDatabase"
            ).build()
        }
    }
    return INSTANCE
}