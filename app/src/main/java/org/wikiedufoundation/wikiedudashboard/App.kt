package org.wikiedufoundation.wikiedudashboard

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.wikiedufoundation.wikiedudashboard.di.*

/**
 * Use the [App] to initialize koin dependency injection
 * for WikiEduDashboardApi
 **/
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@App)

            modules(listOf(apiModule, provideModule, presenterModule, persistenceModule,
                    databaseModule, repositoryModule, viewModelModule))
        }
    }

}