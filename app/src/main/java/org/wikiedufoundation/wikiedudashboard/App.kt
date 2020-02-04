package org.wikiedufoundation.wikiedudashboard

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.wikiedufoundation.wikiedudashboard.di.apiModule
import org.wikiedufoundation.wikiedudashboard.di.persistenceModule
import org.wikiedufoundation.wikiedudashboard.di.databaseModule
import org.wikiedufoundation.wikiedudashboard.di.repositoryModule
import org.wikiedufoundation.wikiedudashboard.di.viewModelModule

/**
 * Use the [App] to initialize koin dependency injection
 * for WikiEduDashboardApi
 **/
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@App)

            modules(listOf(apiModule, persistenceModule,
                    databaseModule, repositoryModule, viewModelModule))
        }
    }
}