package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module

import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider

/**
 * Use the [provideModule] to creating the Providers
 **/
val provideModule = module {

    /**
     * Singleton for [RetrofitProfileProvider]
     **/

    /**
     * Singleton for [RetrofitProfileProvider]
     **/

    /**
     * Singleton for [RetrofitProfileProvider]
     **/

    /**
     * Singleton for [RetrofitProfileProvider]
     **/
    single { RetrofitProfileProvider(get()) }
}