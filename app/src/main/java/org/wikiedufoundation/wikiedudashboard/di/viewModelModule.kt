package org.wikiedufoundation.wikiedudashboard.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.viewmodel.ArticlesEditedViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.viewmodel.CourseDetailViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.viewmodel.StudentsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.viewmodel.RecentActivityViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.viewmodel.CourseUploadsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel.CourseListViewModel
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.viewmodel.DashboardViewModel

/**
 * Use the [viewModelModule] to creating viewModel instance
 **/
val viewModelModule = module {
    viewModel { ActiveCampaignViewModel(get()) }
    viewModel { CourseListViewModel(get()) }
    viewModel { (cookies: String) -> DashboardViewModel(get(), cookies) }
    viewModel { (cookies: String) -> StudentsViewModel(get(), cookies) }
    viewModel { (url: String) -> RecentActivityViewModel(get(), url) }
    viewModel { (url: String) -> CourseUploadsViewModel(get(), url) }
    viewModel { (url: String) -> ArticlesEditedViewModel(get(), url) }
    viewModel { CourseDetailViewModel(get()) }
}