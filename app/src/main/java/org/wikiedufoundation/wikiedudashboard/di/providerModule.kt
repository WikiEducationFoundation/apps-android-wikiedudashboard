package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.RetrofitCourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RetrofitRecentActivityProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.RetrofitMyDashboardProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider

/**
 * Use the [provideModule] to creating the Providers
 **/
val provideModule = module {

    /**
     * Singleton for [RetrofitMyDashboardProvider]
     **/
    single { RetrofitMyDashboardProvider(get()) }

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/
    single { RetrofitMediaDetailsProvider(get()) }

    /**
     * Singleton for [RetrofitProfileProvider]
     **/
    single { RetrofitProfileProvider(get()) }

    /**
     * Singleton for [RetrofitArticlesEditedProvider]
     **/
    single { RetrofitArticlesEditedProvider(get()) }

    /**
     * Singleton for [RetrofitCourseDetailProvider]
     **/
    single { RetrofitCourseDetailProvider(get()) }

    /**
     * Singleton for [RetrofitRecentActivityProvider]
     **/
    single { RetrofitRecentActivityProvider(get()) }

    /**
     * Singleton for [RetrofitStudentListProvider]
     **/
    single { RetrofitStudentListProvider(get()) }

    /**
     * Singleton for [RetrofitCourseUploadsProvider]
     **/
    single { RetrofitCourseUploadsProvider(get()) }

}