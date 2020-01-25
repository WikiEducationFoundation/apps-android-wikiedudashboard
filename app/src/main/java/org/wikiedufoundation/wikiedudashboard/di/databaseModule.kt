package org.wikiedufoundation.wikiedudashboard.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.WikiDatabase
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao

/**
 * Use the [databaseModule] to creating database and dao instance
 **/
val databaseModule = module {

    /**
     * Use the [provideDatabase] to provide a database instance
     * */
    fun provideDatabase(application: Application): WikiDatabase {
        return Room.databaseBuilder(application, WikiDatabase::class.java, "WikiDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    /**
     * Use the [provideCampaignListDao] to provide a provideCampaignListDao instance
     * */
    fun provideCampaignListDao(database: WikiDatabase): ActiveCampaignDao = database.activeCampaignDao

    /**
     * Use the [provideCourseListDao] to provide a provideCourseListDao instance
     * */
    fun provideCourseListDao(database: WikiDatabase): CourseListDao = database.courseListDao

    single { provideDatabase(androidApplication()) }
    single { provideCampaignListDao(get()) }
    single { provideCourseListDao(get()) }
}