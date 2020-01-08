package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.CourseListViewModel

val viewModelModule = module {
    single { ActiveCampaignViewModel(get()) }
    single { CourseListViewModel(get()) }
}