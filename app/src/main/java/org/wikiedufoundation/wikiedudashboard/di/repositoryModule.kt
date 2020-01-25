package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepository
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepository
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepositoryImpl

/**
 * Use the [repositoryModule] to creating repository instance
 **/
val repositoryModule = module {

    /**
     * Use the [provideCampaignListRepository] to provide a CampaignListRepository instance
     * */
    fun provideCampaignListRepository(api: WikiEduDashboardApi, activeCampaignDao: ActiveCampaignDao): ActiveCampaignRepository = ActiveCampaignRepository(api, activeCampaignDao)

    /**
     * Use the [provideCourseListRepository] to provide a CourseListRepository instance
     * */
    fun provideCourseListRepository(api: WikiEduDashboardApi, courseListDao: CourseListDao):
            CourseListRepository = CourseListRepository(api, courseListDao)

    single { provideCampaignListRepository(get(), get()) }
    single { provideCourseListRepository(get(), get()) }
    single <DashboardRepository>{ DashboardRepositoryImpl(get(), get()) }
}