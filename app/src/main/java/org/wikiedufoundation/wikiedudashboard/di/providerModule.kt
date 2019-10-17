package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.RetrofitCampaignListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.RetrofitMyDashboardProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider

/**
 * Use the [provideModule] to creating Providers
 * for WikiEduDashboardApi
 **/
val provideModule = module {

    single { RetrofitCampaignListProvider(get()) }

    single { RetrofitCourseListProvider(get()) }

    single { RetrofitMyDashboardProvider(get()) }

    single { RetrofitMediaDetailsProvider(get()) }

}