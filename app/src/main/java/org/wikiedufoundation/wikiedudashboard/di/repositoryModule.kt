package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepository

val repositoryModule = module {

    fun provideCampaignListRepository(api:WikiEduDashboardApi, activeCampaignDao: ActiveCampaignDao)
            : ActiveCampaignRepository = ActiveCampaignRepository(api, activeCampaignDao)

    fun provideCourseListRepository(api:WikiEduDashboardApi, courseListDao: CourseListDao)
            : CourseListRepository = CourseListRepository(api, courseListDao)

    single { provideCampaignListRepository(get(), get()) }
    single { provideCourseListRepository(get(), get()) }
}
