package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.RetrofitCampaignListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.RetrofitCourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RetrofitRecentActivityProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.RetrofitMyDashboardProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider

/**
 * Use the [provideModule] to creating Providers
 * for WikiEduDashboardApi
 **/
val provideModule = module {

    single { RetrofitCampaignListProvider(get()) }

    single { RetrofitCourseListProvider(get()) }

    single { RetrofitMyDashboardProvider(get()) }

    single { RetrofitMediaDetailsProvider(get()) }

    single { RetrofitProfileProvider(get()) }

    single { RetrofitArticlesEditedProvider(get()) }

    single { RetrofitCourseDetailProvider(get()) }

    single { RetrofitRecentActivityProvider(get()) }

    single { RetrofitStudentListProvider(get()) }

    single { RetrofitCourseUploadsProvider(get()) }

}