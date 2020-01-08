package org.wikiedufoundation.wikiedudashboard.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.WikiDatabase
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.CourseListDao

val databaseModule = module {
    fun provideDatabase(application: Application) : WikiDatabase{
        return Room.databaseBuilder(application, WikiDatabase::class.java, "WikiDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    fun provideCampaignListDao(database: WikiDatabase) : ActiveCampaignDao{
        return database.activeCampaignDao
    }

    fun provideCourseListDao(database: WikiDatabase) : CourseListDao{
        return database.courseListDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCampaignListDao(get()) }
    single { provideCourseListDao(get()) }

}