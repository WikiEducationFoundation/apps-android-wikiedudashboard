package org.wikiedufoundation.wikiedudashboard.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.viewmodel.ArticleViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.viewmodel.CampaignHomeViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.viewmodel.ArticlesEditedViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.viewmodel.CourseDetailViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.viewmodel.StudentsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.viewmodel.RecentActivityViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.viewmodel.CourseUploadsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel.CourseListViewModel
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.viewmodel.DashboardViewModel
import org.wikiedufoundation.wikiedudashboard.ui.profile.viewmodel.ProfileViewModel
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.viewmodel.MediaDetailsViewModel

/**
 * Use the [viewModelModule] to creating viewModel instance
 **/
val viewModelModule = module {
    viewModel { (cookies: String) -> ActiveCampaignViewModel(get(), cookies) }
    viewModel { (cookies: String) -> CourseListViewModel(get(), cookies) }
    viewModel { (cookies: String) -> DashboardViewModel(get(), cookies) }
    viewModel { (cookies: String) -> StudentsViewModel(get(), cookies) }
    viewModel { (url: String) -> RecentActivityViewModel(get(), url) }
    viewModel { (url: String) -> CourseUploadsViewModel(get(), url) }
    viewModel { (url: String) -> ArticlesEditedViewModel(get(), url) }
    viewModel { CourseDetailViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { (cookies: String) -> MediaDetailsViewModel(get(), cookies) }
    viewModel { CampaignHomeViewModel(get()) }
    viewModel { (url: String) -> ArticleViewModel(get(), url) }
}