package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider

/**
 * Use the [provideModule] to creating the Providers
 **/
val provideModule = module {

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/
    single { RetrofitMediaDetailsProvider(get()) }


}