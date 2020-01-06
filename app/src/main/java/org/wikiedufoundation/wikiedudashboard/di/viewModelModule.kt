package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.Viewmodel.ActiveCampaignViewModel

val viewModelModule = module {
    single { ActiveCampaignViewModel(get()) }
}