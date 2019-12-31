package org.wikiedufoundation.wikiedudashboard.di

import androidx.room.Room
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs

/**
 * Use the [persistenceModule] to creating shared preference instance
 **/
val persistenceModule = module {

    /**
     * Singleton for shared preference
     **/
    single { SharedPrefs(get()) }

}