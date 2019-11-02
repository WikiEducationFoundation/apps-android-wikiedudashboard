package org.wikiedufoundation.wikiedudashboard.data.localDatabse

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.dao.ActiveCampaignDao

abstract class WikiDatabase : RoomDatabase() {
    abstract val activeCampaignDao : ActiveCampaignDao

}

private lateinit var INSTANCE : WikiDatabase

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