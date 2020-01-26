package org.wikiedufoundation.wikiedudashboard.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel.CourseListViewModel
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.viewmodel.DashboardViewModel

/**
 * Use the [viewModelModule] to creating viewModel instance
 **/
val viewModelModule = module {
    viewModel { ActiveCampaignViewModel(get()) }
    viewModel { CourseListViewModel(get()) }
    viewModel  { (cookies :String) -> DashboardViewModel(get(), cookies) }
}